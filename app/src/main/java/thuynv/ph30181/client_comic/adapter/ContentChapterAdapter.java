package thuynv.ph30181.client_comic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import thuynv.ph30181.client_comic.R;

public class ContentChapterAdapter extends RecyclerView.Adapter<ContentChapterAdapter.ContentViewHolder> {
    private List<String> contentPhotos;

    public ContentChapterAdapter(List<String> contentPhotos) {
        this.contentPhotos = contentPhotos;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item_layout, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        String imageUrl = contentPhotos.get(position);
        Picasso.get().load(imageUrl).into(holder.imgContent);
    }

    @Override
    public int getItemCount() {
        return contentPhotos.size();
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContent;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.imgContent);
        }
    }
}
