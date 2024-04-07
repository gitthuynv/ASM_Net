package thuynv.ph30181.client_comic.request.Comment;

import com.google.gson.annotations.SerializedName;

public class CreateCommentRequest {
    @SerializedName("idComic")
    private String idComic;
    @SerializedName("content")
    private String content;
    @SerializedName("uid")
    private String uid;

    public CreateCommentRequest(String idComic,String content, String uid) {
        this.idComic = idComic;
        this.content = content;
        this.uid = uid;
    }
}



