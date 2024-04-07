package thuynv.ph30181.client_comic.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuynv.ph30181.client_comic.R;
import thuynv.ph30181.client_comic.adapter.ChapterAdapter;
import thuynv.ph30181.client_comic.adapter.CommentAdapter;
import thuynv.ph30181.client_comic.api.Api_Service;
import thuynv.ph30181.client_comic.models.ComicComments;
import thuynv.ph30181.client_comic.models.Comics;
import thuynv.ph30181.client_comic.request.Comment.CreateCommentRequest;

public class ComicCommentActivity extends AppCompatActivity {
    private CommentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comic_comment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Comics comic = (Comics) getIntent().getSerializableExtra("comic_comment");
        List<ComicComments> listComment = getComment(comic.get_id());

        RecyclerView recyclerViewComments = findViewById(R.id.rcvComicComment);
        Button btnAddCommentComic = findViewById(R.id.btnAddCommentComic);
        adapter = new CommentAdapter(this, listComment);
        recyclerViewComments.setAdapter(adapter);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));
        addComment(comic.get_id());


        btnAddCommentComic.setOnClickListener(v -> {
            addComment(comic.get_id());
        });
    }
    public List<ComicComments> getComment(String idComic){
        List<ComicComments> listComment = new ArrayList<>();
        Api_Service.API_SERVICE.getComment(idComic).enqueue(new Callback<List<ComicComments>>() {
            @Override
            public void onResponse(Call<List<ComicComments>> call, Response<List<ComicComments>> response) {
                if(!response.isSuccessful()) {
                    return;
                }
                listComment.addAll(response.body());
                Collections.reverse(listComment);
                adapter.setData(listComment);

            }

            @Override
            public void onFailure(Call<List<ComicComments>> call, Throwable t) {
            }
        });
        return listComment;
    };
    public void addComment(String idComic){
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", ""); // Đảm bảo key là đúng
        EditText edtCommentComic = findViewById(R.id.edtCommentComic);
        List<ComicComments> listComment = getComment(idComic);
        String comment = edtCommentComic.getText().toString();
        if(!comment.isEmpty()){
            CreateCommentRequest comments = new CreateCommentRequest(idComic, comment,uid);
            Api_Service.API_SERVICE.createComment(comments).enqueue(new Callback<ComicComments>() {
                @Override
                public void onResponse(Call<ComicComments> call, Response<ComicComments> response) {
                    if (response.isSuccessful()) {
                        listComment.add(0,response.body());
                        edtCommentComic.setText("");
                        adapter.setData(listComment);
                    }
                }

                @Override
                public void onFailure(Call<ComicComments> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}