package thuynv.ph30181.client_comic.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuynv.ph30181.client_comic.R;
import thuynv.ph30181.client_comic.api.Api_Service;
import thuynv.ph30181.client_comic.models.ComicComments;
import thuynv.ph30181.client_comic.request.Comment.DeleteCommentRequest;
import thuynv.ph30181.client_comic.request.Comment.UpdateCommentRequest;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context context;
    private List<ComicComments> commentList;
    private OnCommentClickListener onCommentClickListener;

    public CommentAdapter(Context context, List<ComicComments> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    public void setOnCommentClickListener(OnCommentClickListener listener) {
        this.onCommentClickListener = listener;
    }

    public void setData(List<ComicComments> comments) {
        this.commentList = comments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item_layout, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        ComicComments comment = commentList.get(position);
        holder.tvTenUserComment.setText(comment.getUsername());
        holder.tvNoiDungComment.setText(comment.getComment());
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", ""); // Đảm bảo key là đúng

        if(comment.getId() != uid) {
            holder.tvXoaComment.setVisibility(View.GONE);
            holder.tvSuaComment.setVisibility(View.GONE);
        }

        holder.tvXoaComment.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Bạn có muốn xóa bình luận này không?");
            builder.setPositiveButton("Có", (dialog, which) -> {

                Api_Service.API_SERVICE.deleteComment(comment.getId(), new DeleteCommentRequest(uid)).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.isSuccessful()) {
                            commentList.remove(comment);
                            setData(commentList);
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            });
            builder.setNegativeButton("Không", (dialog, which) -> {
                dialog.dismiss();
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });


        holder.tvSuaComment.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.update_dialog_layout, null);
            builder.setView(dialogView);

            EditText edtUpdateComment = dialogView.findViewById(R.id.edtUpdateComment);
            edtUpdateComment.setText(comment.getComment());
            Button btnHuyUpdate = dialogView.findViewById(R.id.btnHuyUpdate);
            Button btnUpdateComicComment = dialogView.findViewById(R.id.btnUpdateComicComment);

            AlertDialog dialog = builder.create();

            btnHuyUpdate.setOnClickListener(v -> dialog.dismiss());

            btnUpdateComicComment.setOnClickListener(v -> {
//                SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", MODE_PRIVATE);
//                String uid = sharedPreferences.getString("uid", ""); // Đảm bảo key là đúng
                String updatedComments = edtUpdateComment.getText().toString();
                Api_Service.API_SERVICE.updateComment(comment.getId(), new UpdateCommentRequest(updatedComments, uid)).enqueue(new Callback<ComicComments>() {
                    @Override
                    public void onResponse(Call<ComicComments> call, Response<ComicComments> response) {
                        for (ComicComments cm : commentList) {
                            if (cm.getId().equals(comment.getId())) {
                                cm.setComment(updatedComments);
                            }
                        }
                        setData(commentList);

                    }

                    @Override
                    public void onFailure(Call<ComicComments> call, Throwable t) {

                    }
                });

                dialog.dismiss();
            });

            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenUserComment;
        TextView tvNoiDungComment;
        TextView tvXoaComment;
        TextView tvSuaComment;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenUserComment = itemView.findViewById(R.id.tvTenUserComment);
            tvNoiDungComment = itemView.findViewById(R.id.tvNoiDungComment);
            tvXoaComment = itemView.findViewById(R.id.tvXoaComment);
            tvSuaComment = itemView.findViewById(R.id.tvSuaComment);
        }
    }

    public interface OnCommentClickListener {
        void onDeleteClick(int position);

        void onEditClick(int position);
    }
}
