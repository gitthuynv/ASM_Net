package thuynv.ph30181.client_comic.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuynv.ph30181.client_comic.R;
import thuynv.ph30181.client_comic.api.Api_Service;
import thuynv.ph30181.client_comic.models.Comics;
import thuynv.ph30181.client_comic.views.ContentChapterActivity;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    private List<Comics.Chapter> chapters;
    private Context context;
    private OnItemClickListener listener;
    private List<Comics> comicList;

    public interface OnItemClickListener {
        void onItemClick(Comics.Chapter chapter);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ChapterAdapter(Context context, List<Comics.Chapter> chapters) {
        this.context = context;
        this.chapters = chapters;
    }

    public void setComicList(List<Comics> comicList) {
        this.comicList = comicList;
        notifyDataSetChanged(); // Cập nhật RecyclerView khi danh sách truyện thay đổi
    }


    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_item_layout, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Comics.Chapter chapter = chapters.get(position);
        holder.bind(chapter);
        holder.lnContentItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(chapter);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    static class ChapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvChapterTitle;
        LinearLayout lnContentItem;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapterTitle = itemView.findViewById(R.id.tvChapterTitle);
            lnContentItem = itemView.findViewById(R.id.chapter_item_layout_linner);
        }

        public void bind(Comics.Chapter chapter) {
            tvChapterTitle.setText(String.valueOf(chapter.getChapterNumber()));
        }
    }

}
