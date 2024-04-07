package thuynv.ph30181.client_comic.models;

import java.io.Serializable;
import java.util.List;

public class Comics implements Serializable {
    private String _id;
    private String comicName;
    private String author;
    private String category;
    private String describe;
    private int yearOfCreation;
    private List<String> coverImage;
    private List<Chapter> chapters;
    private List<ComicComment> comicComments;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public List<String> getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(List<String> coverImage) {
        this.coverImage = coverImage;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<ComicComment> getComicComments() {
        return comicComments;
    }

    public void setComicComments(List<ComicComment> comicComments) {
        this.comicComments = comicComments;
    }

    public static class Chapter implements Serializable{
        private int chapterNumber;
        private List<String> contentPhoto;

        public int getChapterNumber() {
            return chapterNumber;
        }

        public void setChapterNumber(int chapterNumber) {
            this.chapterNumber = chapterNumber;
        }

        public List<String> getContentPhoto() {
            return contentPhoto;
        }

        public void setContentPhoto(List<String> contentPhoto) {
            this.contentPhoto = contentPhoto;
        }

        @Override
        public String toString() {
            return "Chapter " + chapterNumber;
        }
    }

    public static class ComicComment implements Serializable{
        private String username;
        private String comment;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    @Override
    public String toString() {
        return "Comics{" +
                "comicName='" + comicName + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", describe='" + describe + '\'' +
                ", yearOfCreation=" + yearOfCreation +
                ", coverImage=" + coverImage +
                ", chapters=" + chapters +
                ", comicComments=" + comicComments +
                '}';
    }
}
