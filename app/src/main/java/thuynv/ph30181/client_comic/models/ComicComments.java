package thuynv.ph30181.client_comic.models;

import com.google.gson.annotations.SerializedName;

public class ComicComments {
    @SerializedName("id")
    private String id;
    @SerializedName("idComic")
    private String idComic;
    @SerializedName("comment")
    private String comment;
    @SerializedName("username")
    private String username;
    @SerializedName("uid")
    private String uid;

    public ComicComments(String id, String idComic, String comment, String username, String uid) {
        this.id = id;
        this.idComic = idComic;
        this.comment = comment;
        this.username = username;
        this.uid = uid;
    }

    public ComicComments() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdComic() {
        return idComic;
    }

    public void setIdComic(String idComic) {
        this.idComic = idComic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "comicComments{" +
                "id='" + id + '\'' +
                ", idComic='" + idComic + '\'' +
                ", comment='" + comment + '\'' +
                ", username='" + username + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
