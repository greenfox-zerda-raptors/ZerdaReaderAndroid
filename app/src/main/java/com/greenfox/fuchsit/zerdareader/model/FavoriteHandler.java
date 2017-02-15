package com.greenfox.fuchsit.zerdareader.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.dialog.FavoriteErrorDialog;
import com.greenfox.fuchsit.zerdareader.event.FavoriteSavedEvent;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by regnisalram on 2/10/17.
 */

public class FavoriteHandler implements FavoriteErrorDialog.FavoriteErrorListener{

    @Inject
    ReaderApiInterface apiService;

    SharedPreferences sharedPreferences;

    AppCompatActivity activity;

    public FavoriteHandler(AppCompatActivity activity) {

        this.activity = activity;

        DaggerMockServerComponent.builder().build().inject(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    public void createFavoriteCall(final long itemId) {
        Call <FavoriteResponse> createCall = apiService.createFavoriteItem(sharedPreferences.getString("token", null), new FavoriteRequest(itemId));
        createCall.enqueue(new Callback<FavoriteResponse>() {
            @Override
            public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
                FavoriteResponse favoriteResponse = response.body();
                if (favoriteResponse.getResponse().equals("success")) {
                    EventBus.getDefault().post(new FavoriteSavedEvent(itemId));
                } else {
                    showFavoriteErrorDialog("Couldn't save favorite at this time. Try again?", true, itemId);
                }
            }

            @Override
            public void onFailure(Call<FavoriteResponse> call, Throwable t) {

            }
        });
    }

    public void deleteFavoriteCall(final long itemId) {
        Call<FavoriteResponse> deleteCall = apiService.deleteFavoriteItem(sharedPreferences.getString("token", null), new FavoriteRequest(itemId));
        deleteCall.enqueue(new Callback<FavoriteResponse>() {
            @Override
            public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
                FavoriteResponse favoriteResponse = response.body();
                if (favoriteResponse.getResponse().equals("success")) {
                    EventBus.getDefault().post(new FavoriteSavedEvent(itemId));
                } else {
                    showFavoriteErrorDialog("Couldn't remove favorite at this time. Try again?", false, itemId);
                }
            }

            @Override
            public void onFailure(Call<FavoriteResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, boolean isFavoriting, long id) {
        if (isFavoriting) {
            createFavoriteCall(id);
        } else {
            deleteFavoriteCall(id);
        }
    }

    private void showFavoriteErrorDialog(String message, boolean isFavoriting, long item_id) {
        FavoriteErrorDialog newFragment = new FavoriteErrorDialog(message, isFavoriting, item_id);
        newFragment.show(activity.getSupportFragmentManager(), "favoriteError");
    }
}
