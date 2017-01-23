package com.greenfox.fuchsit.zerdareader.server;

import com.greenfox.fuchsit.zerdareader.model.UserResponse;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Zsuzska on 2017. 01. 23..
 */

public class MockCall implements Call<UserResponse> {
    @Override
    public Response<UserResponse> execute() throws IOException {
        return null;
    }

    @Override
    public void enqueue(Callback<UserResponse> callback) {

    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<UserResponse> clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}
