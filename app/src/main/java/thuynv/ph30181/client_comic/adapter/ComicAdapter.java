package thuynv.ph30181.client_comic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import thuynv.ph30181.client_comic.R;
import thuynv.ph30181.client_comic.models.Comics;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder> {
    private List<Comics> comicList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Comics comic);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }




    // Constructor
    public ComicAdapter(Context context, List<Comics> comicList) {
        this.context = context;
        this.comicList = comicList;
    }

    public void setComicList(List<Comics> comicList) {
        this.comicList = comicList;
        notifyDataSetChanged(); // Cập nhật RecyclerView khi danh sách truyện thay đổi
    }

    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_item_layout, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {
        Comics comic = comicList.get(position);

        holder.tvTenTruyen.setText(comic.getComicName());
        holder.tvTheLoai.setText(comic.getCategory());
        holder.tvTacGia.setText(comic.getAuthor());
        holder.tvNamXB.setText(String.valueOf(comic.getYearOfCreation()));
        String imageUrl = comic.getCoverImage().get(0);
        Picasso.get().load(imageUrl).into(holder.imgComic);

        holder.cvComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(comic);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    static class ComicViewHolder extends RecyclerView.ViewHolder {
        ImageView imgComic;
        TextView tvTenTruyen, tvTheLoai, tvTacGia, tvNamXB;
        CardView cvComic;

        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            imgComic = itemView.findViewById(R.id.imgComic);
            tvTenTruyen = itemView.findViewById(R.id.tvTenTruyen);
            tvTheLoai = itemView.findViewById(R.id.tvTheLoai);
            tvTacGia = itemView.findViewById(R.id.tvTacGia);
            tvNamXB = itemView.findViewById(R.id.tvNamXB);
            cvComic = itemView.findViewById(R.id.cvComic);
        }

    }
}