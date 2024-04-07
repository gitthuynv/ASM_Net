package thuynv.ph30181.client_comic.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import thuynv.ph30181.client_comic.models.ComicComments;
import thuynv.ph30181.client_comic.models.Comics;
import thuynv.ph30181.client_comic.models.Users;
import thuynv.ph30181.client_comic.request.Comment.CreateCommentRequest;
import thuynv.ph30181.client_comic.request.Comment.DeleteCommentRequest;
import thuynv.ph30181.client_comic.request.Comment.UpdateCommentRequest;

public interface Api_Service {
    Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    Api_Service API_SERVICE = new Retrofit.Builder().baseUrl("http://192.168.0.106:2000").addConverterFactory(GsonConverterFactory.create(GSON)).build().create(Api_Service.class);

    @POST("/login")
    Call<Users> loginUser(@Body Users users);

    @POST("/register")
    Call<Users> registerUser(@Body Users users);

    @GET("/listcomics")
    Call<List<Comics>> getDataComic();

    @GET("/comic/{id}")
    Call<Comics> getComicById(@Path("id") String id);

    @GET("/comics/{id}/chapters")
    Call<List<Comics.Chapter>> getChapters(@Path("id") String id);

    @GET("/comment")
    Call<List<ComicComments>> getComment(@Query("idComic") String idComic);

    @POST("/comment")
    Call<ComicComments> createComment(@Body CreateCommentRequest request);

    @POST("/comment-delete")
    Call<Boolean> deleteComment(@Query("idComment") String idComic, @Body DeleteCommentRequest request);

    @PUT("/comment-update")
    Call<ComicComments> updateComment(@Query("idComment") String idComic, @Body UpdateCommentRequest request);


}
