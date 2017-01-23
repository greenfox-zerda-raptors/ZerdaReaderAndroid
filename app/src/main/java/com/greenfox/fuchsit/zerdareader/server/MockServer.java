package com.greenfox.fuchsit.zerdareader.server;

import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by Zsuzska on 2017. 01. 20..
 */

public class MockServer implements ReaderApiInterface {
    @Override
    public Call<UserResponse> loginUser(@Query("username") String username, @Query("password") String password) {
        return null;
    }
}
