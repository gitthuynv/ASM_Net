package thuynv.ph30181.client_comic.request.Comment;

import com.google.gson.annotations.SerializedName;

public class UpdateCommentRequest {
    @SerializedName("content")
    private String content;
    @SerializedName("uid")
    private String uid;

    public UpdateCommentRequest(String content, String uid) {
        this.content = content;
        this.uid = uid;
    }
}
