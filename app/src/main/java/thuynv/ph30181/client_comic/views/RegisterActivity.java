package thuynv.ph30181.client_comic.views;

import android.content.Intent;
import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuynv.ph30181.client_comic.R;
import thuynv.ph30181.client_comic.api.Api_Service;
import thuynv.ph30181.client_comic.models.Users;

public class RegisterActivity extends AppCompatActivity {
    EditText edtDKUsername, edtDKPassword, edtConfirmPassword;
    TextView tvDangNhap;
    Button btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            edtDKUsername = findViewById(R.id.edtDKUsername);
            edtDKPassword = findViewById(R.id.edtDKPassword);
            edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
            btnDangKy = findViewById(R.id.btnDangNhap);
            tvDangNhap = findViewById(R.id.tvDangNhap);

            btnDangKy.setOnClickListener(view -> {
                String username = edtDKUsername.getText().toString();
                String password = edtDKPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();

                // Kiểm tra xác nhận mật khẩu
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu xác nhận không khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Users user = new Users();
                user.setUsername(username);
                user.setPassword(password);

                Api_Service.API_SERVICE.registerUser(user).enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                        if (response.isSuccessful()) {
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Đăng ký không thành công! Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Đã xảy ra lỗi! Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
                    }
                });
            });

            tvDangNhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            });
            return insets;
        });
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