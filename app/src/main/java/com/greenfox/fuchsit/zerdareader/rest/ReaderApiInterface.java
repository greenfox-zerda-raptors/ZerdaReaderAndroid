package com.greenfox.fuchsit.zerdareader.rest;

import com.greenfox.fuchsit.zerdareader.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Zsuzska on 2017. 01. 19..
 */

public interface ReaderApiInterface {

    @GET("user/login")
    Call<UserResponse> loginUser(@Query("username") String username,
                                 @Query("password") String password);

}
