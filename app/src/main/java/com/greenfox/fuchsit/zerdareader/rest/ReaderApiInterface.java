package com.greenfox.fuchsit.zerdareader.rest;

import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Zsuzska on 2017. 01. 19..
 */

public interface ReaderApiInterface {

    @GET("/favorites")
    Call<ArrayList<NewsItem>> getFavouriteNewsItems();

    @GET("/messages")
    Call<ArrayList<NewsItem>> getNewsItems();

    @POST("user/login")
    Call<UserResponse> loginUser(@Query("username") String username,
                                 @Query("password") String password);
}


