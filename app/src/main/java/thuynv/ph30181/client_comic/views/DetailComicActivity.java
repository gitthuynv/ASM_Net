package thuynv.ph30181.client_comic.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuynv.ph30181.client_comic.R;
import thuynv.ph30181.client_comic.adapter.ChapterAdapter;
import thuynv.ph30181.client_comic.api.Api_Service;
import thuynv.ph30181.client_comic.models.Comics;

public class DetailComicActivity extends AppCompatActivity {
    private ChapterAdapter chapterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_comic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnCOMMENT = findViewById(R.id.btnCOMMENT);


        Comics comic = (Comics) getIntent().getSerializableExtra("comic_detail");

        if (comic != null) {
            ImageView imgCover = findViewById(R.id.img_cover);
            TextView tvTenTruyen = findViewById(R.id.tv_ten_truyen);
            TextView tvTacGia = findViewById(R.id.tv_tac_gia);
            TextView tvNamXB = findViewById(R.id.tv_nam_xb);
            TextView tvMoTa = findViewById(R.id.tv_mo_ta);


            Picasso.get().load(comic.getCoverImage().get(0)).into(imgCover);
            tvTenTruyen.setText(comic.getComicName());
            tvTacGia.setText(comic.getAuthor());
            tvNamXB.setText(String.valueOf(comic.getYearOfCreation()));
            tvMoTa.setText(comic.getDescribe());

            Api_Service.API_SERVICE.getChapters(comic.get_id()).enqueue(new Callback<List<Comics.Chapter>>() {
                @Override
                public void onResponse(Call<List<Comics.Chapter>> call, Response<List<Comics.Chapter>> response) {
                    if (response.isSuccessful()) {
                        List<Comics.Chapter> chapters = response.body();
                        RecyclerView recyclerViewChapters = findViewById(R.id.recyclerViewChapter);
                        recyclerViewChapters.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        chapterAdapter = new ChapterAdapter(getApplicationContext(), chapters);
                        recyclerViewChapters.setAdapter(chapterAdapter);
                        chapterAdapter.setOnItemClickListener(chapter -> {
                            Intent intent = new Intent(getApplicationContext(), ContentChapterActivity.class);
                            intent.putExtra("chapter_content", (Serializable) chapter);
                            startActivity(intent);
                        });
                    }
                }

                @Override
                public void onFailure(Call<List<Comics.Chapter>> call, Throwable t) {
                }
            });
        }
        btnCOMMENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ComicCommentActivity.class);
                intent.putExtra("comic_comment", (Serializable)comic);
                startActivity(intent);
            }
        });

    }



}
