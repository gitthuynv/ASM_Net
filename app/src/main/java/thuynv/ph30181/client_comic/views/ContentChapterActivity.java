package thuynv.ph30181.client_comic.views;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import thuynv.ph30181.client_comic.R;
import thuynv.ph30181.client_comic.adapter.ContentChapterAdapter;
import thuynv.ph30181.client_comic.models.Comics;

public class ContentChapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_content_chapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Comics.Chapter chapter = (Comics.Chapter) getIntent().getSerializableExtra("chapter_content");
        RecyclerView recyclerView = findViewById(R.id.recyclerViewContent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up adapter with content photos
        ContentChapterAdapter adapter = new ContentChapterAdapter(chapter.getContentPhoto());
        recyclerView.setAdapter(adapter);
    }
}