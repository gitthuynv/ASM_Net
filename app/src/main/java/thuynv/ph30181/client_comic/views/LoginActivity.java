package thuynv.ph30181.client_comic.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuynv.ph30181.client_comic.R;
import thuynv.ph30181.client_comic.api.Api_Service;
import thuynv.ph30181.client_comic.models.Users;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    TextView tvDangKy;
    Button btnDangNhap;
    ExecutorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            edtUsername = findViewById(R.id.edtUsername);
            edtPassword = findViewById(R.id.edtPassword);
            tvDangKy = findViewById(R.id.tvDangKy);
            btnDangNhap = findViewById(R.id.btnDangNhap);

            service = Executors.newCachedThreadPool();

            btnDangNhap.setOnClickListener(v1 -> {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                Users user = new Users();
                user.setUsername(username);
                user.setPassword(password);
                if (edtUsername.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }
                else{
                    Api_Service.API_SERVICE.loginUser(user).enqueue(new Callback<Users>() {
                        @Override
                        public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                            if (response.isSuccessful()) {
                                Users user = response.body();
                                if (user != null) {
                                    String uid = user.get_id();
                                    saveUidToSharedPreferences(uid);
                                    startActivity(new Intent(LoginActivity.this, Tabbar.class));
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
                            Toast.makeText(LoginActivity.this, "Đã xảy ra lỗi! Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            tvDangKy.setOnClickListener(v12 -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

            return insets;
        });
    }
    private void saveUidToSharedPreferences(String uid) {
        Log.d("UID", uid);
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("uid", uid);
        editor.commit();
        editor.apply();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}