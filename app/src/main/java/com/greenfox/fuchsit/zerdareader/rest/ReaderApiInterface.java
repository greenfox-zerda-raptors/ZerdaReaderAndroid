package com.greenfox.fuchsit.zerdareader.rest;

import com.greenfox.fuchsit.zerdareader.model.UserResponse;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Zsuzska on 2017. 01. 19..
 */

public interface ReaderApiInterface {

    @POST("user/login")
    Call<UserResponse> loginUser(@Query("username") String username,
                                 @Query("password") String password);
}
