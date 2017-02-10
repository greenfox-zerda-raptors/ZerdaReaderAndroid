package com.greenfox.fuchsit.zerdareader.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.greenfox.fuchsit.zerdareader.activity.DetailedPageActivity;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by regnisalram on 2/10/17.
 */

public class FavoriteHandler {

    Call<FavoriteResponse> createCall;
    Call<FavoriteResponse> deleteCall;

    @Inject
    ReaderApiInterface apiService;

    SharedPreferences sharedPreferences;

    FavoriteRequest favoriteRequest;

    boolean reply;

    public FavoriteHandler(DetailedPageActivity detailedPageActivity) {

        DaggerMockServerComponent.builder().build().inject(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(detailedPageActivity);

        createCall = apiService.createFavoriteItem(sharedPreferences.getString("token", null), favoriteRequest);
        deleteCall = apiService.deleteFavoriteItem(sharedPreferences.getString("token", null), favoriteRequest);
    }


    public boolean createFavoriteCall() {
        createCall.enqueue(new Callback<FavoriteResponse>() {
            @Override
            public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
                FavoriteResponse favoriteResponse = response.body();
                if (favoriteResponse.getResponse().equals("success")) {
                    reply = true;
                } else {
                    reply = false;
                }
            }

            @Override
            public void onFailure(Call<FavoriteResponse> call, Throwable t) {

            }
        });
        return reply;
    }

    public boolean deleteFavoriteCall() {
        deleteCall.enqueue(new Callback<FavoriteResponse>() {
            @Override
            public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
                FavoriteResponse favoriteResponse = response.body();
                if (favoriteResponse.getResponse().equals("success")) {
                    reply = true;
                } else {
                    reply = false;
                }
            }

            @Override
            public void onFailure(Call<FavoriteResponse> call, Throwable t) {

            }
        });
        return reply;
    }
}
