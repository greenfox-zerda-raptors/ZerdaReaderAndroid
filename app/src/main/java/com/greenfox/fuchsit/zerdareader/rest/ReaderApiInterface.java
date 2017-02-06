package com.greenfox.fuchsit.zerdareader.rest;

import android.content.Intent;

import com.greenfox.fuchsit.zerdareader.model.LoginRequest;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.UpdateRequest;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Zsuzska on 2017. 01. 19..
 */

public interface ReaderApiInterface {

    @GET("/favorites")
    Call<ArrayList<NewsItem>> getFavouriteNewsItems();

    @GET("/feed")
    Call<ArrayList<NewsItem>> getNewsItems();

    @POST("user/login")
    Call<UserResponse> loginUser(LoginRequest loginRequest);

    @PUT("/feed/{item_id}")
    void updateOpened(@Path("item_id") long id, UpdateRequest updateRequest);

    @POST("user/signup")
    Call<UserResponse> signUpUser(LoginRequest loginRequest);

}


