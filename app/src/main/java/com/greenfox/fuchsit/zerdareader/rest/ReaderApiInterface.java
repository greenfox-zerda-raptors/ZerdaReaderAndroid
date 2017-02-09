package com.greenfox.fuchsit.zerdareader.rest;

import com.greenfox.fuchsit.zerdareader.model.LoginRequest;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteRequest;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteResponse;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionModel;
import com.greenfox.fuchsit.zerdareader.model.UpdateRequest;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.DELETE;
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

    @GET("/subscriptions")
    Call<ArrayList<SubscriptionModel>> getSubscriptions();

    @POST("/subscribe")
    Call<SubscriptionModel> addNewSubscription();

    @DELETE("/subsribe/{id}")
    Call<SubsDeleteResponse> deleteSubscription(@Path("id") long id, SubsDeleteRequest subsDeleteRequest);

}


