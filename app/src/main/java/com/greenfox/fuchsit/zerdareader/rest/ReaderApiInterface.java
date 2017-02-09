package com.greenfox.fuchsit.zerdareader.rest;

import com.greenfox.fuchsit.zerdareader.model.FavoriteRequest;
import com.greenfox.fuchsit.zerdareader.model.FavoriteResponse;
import com.greenfox.fuchsit.zerdareader.model.LoginRequest;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.UpdateRequest;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Zsuzska on 2017. 01. 19..
 */

public interface ReaderApiInterface {

    @GET("/feed")
    Call<ArrayList<NewsItem>> getNewsItems();

    //favourite cc anna
    @GET("/favorites")
    Call<ArrayList<NewsItem>> getFavouriteNewsItems(@Query("token") String token);

    @POST("/favorites")
    Call<FavoriteResponse> createFavoriteItem(@Query("token") String token, FavoriteRequest favoriteRequest);

    @DELETE("/favorites")
    Call<FavoriteResponse> deleteFavoriteItem(@Query("token") String token, FavoriteRequest favoriteRequest);

    @POST("user/login")
    Call<UserResponse> loginUser(LoginRequest loginRequest);

    @PUT("/feed/{item_id}")
    void updateOpened(@Path("item_id") long id, UpdateRequest updateRequest);

    @POST("user/signup")
    Call<UserResponse> signUpUser(LoginRequest loginRequest);



}


