package com.greenfox.fuchsit.zerdareader.server;

import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zsuzska on 2017. 01. 20..
 */

public class MockServer implements ReaderApiInterface {
    @Override
    public MockCall<UserResponse> loginUser(String username, String password) {
        return new MockCall<UserResponse>() {
            @Override
            public void enqueue(Callback<UserResponse> callback) {
                Response<UserResponse> r = Response.success(new UserResponse());
                callback.onResponse(this, r);
            }
        };
    }
}
