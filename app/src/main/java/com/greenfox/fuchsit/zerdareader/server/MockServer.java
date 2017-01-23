package com.greenfox.fuchsit.zerdareader.server;

import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Query;

/**
 * Created by Zsuzska on 2017. 01. 20..
 */

public class MockServer implements ReaderApiInterface {
    @Override
    public MockCall<UserResponse> loginUser(String username, String password) {
        return new MockCall<UserResponse>() {
            @Override
            public void enqueue(Callback<UserResponse> callback) {
                super.enqueue(callback);
            }
        };
    }
}
