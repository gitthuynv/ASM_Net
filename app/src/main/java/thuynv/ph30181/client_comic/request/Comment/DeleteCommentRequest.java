package thuynv.ph30181.client_comic.request.Comment;

import com.google.gson.annotations.SerializedName;

public class DeleteCommentRequest {
    @SerializedName("uid")
    private String uid;

    public DeleteCommentRequest(String uid) {
        this.uid = uid;
    }
}
