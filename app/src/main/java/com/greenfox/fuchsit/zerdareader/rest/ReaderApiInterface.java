package com.greenfox.fuchsit.zerdareader.rest;

import com.greenfox.fuchsit.zerdareader.model.AddSubsRequest;
import com.greenfox.fuchsit.zerdareader.model.AddSubsResponse;
import com.greenfox.fuchsit.zerdareader.model.FavoriteRequest;
import com.greenfox.fuchsit.zerdareader.model.FavoriteResponse;
import com.greenfox.fuchsit.zerdareader.model.FeedResponse;
import com.greenfox.fuchsit.zerdareader.model.LoginRequest;

import com.greenfox.fuchsit.zerdareader.model.SubsDeleteRequest;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteResponse;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionResponse;
import com.greenfox.fuchsit.zerdareader.model.UpdateRequest;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Zsuzska on 2017. 01. 19..
 */

public interface ReaderApiInterface {

    @GET("/feed")
    Call<FeedResponse> getNewsItems(@Query("token") String token);

    //favourite cc anna
    @GET("/favorites")
    Call<FeedResponse> getFavouriteNewsItems(@Query("token") String token);

    @POST("/favorites")
    Call<FavoriteResponse> createFavoriteItem(@Query("token") String token, @Body FavoriteRequest favoriteRequest);

    @HTTP(method = "DELETE", path = "/favorites", hasBody = true)
    Call<FavoriteResponse> deleteFavoriteItem(@Query("token") String token, @Body FavoriteRequest favoriteRequest);

    @POST("user/login")
    Call<UserResponse> loginUser(@Body LoginRequest loginRequest);

    @PUT("/feed/{item_id}")
    Call<okhttp3.ResponseBody> updateOpened(@Path("item_id") long id, @Query("token") String token, @Body UpdateRequest updateRequest);

    @POST("user/signup")
    Call<UserResponse> signUpUser(@Body LoginRequest loginRequest);

    @GET("/subscriptions")
    Call<SubscriptionResponse> getSubscriptions(@Query("token") String token);

    @POST("/subscribe")
    Call<AddSubsResponse> addNewSubscription(@Query("token") String token, @Body AddSubsRequest addSubsRequest);

    @DELETE("/subscribe/{id}")
    Call<SubsDeleteResponse> deleteSubscription(@Path("id") long id, SubsDeleteRequest subsDeleteRequest, @Query("token") String token);

}


