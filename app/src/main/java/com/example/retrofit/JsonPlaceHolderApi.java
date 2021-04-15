package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPost(@Query("userId") int userId);

    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id")int postId);

}
