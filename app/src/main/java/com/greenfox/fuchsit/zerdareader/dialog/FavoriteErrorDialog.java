package com.greenfox.fuchsit.zerdareader.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;

/**
 * Created by regnisalram on 2/9/17.
 */

public class FavoriteErrorDialog extends DialogFragment {

    String message;
    boolean isFavoriting;

    FavoriteErrorListener favoriteErrorListener;

    public FavoriteErrorDialog(String message, boolean isFavoriting) {
        this.message = message;
        this.isFavoriting = isFavoriting;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.dialog_favorite_error);
        builder.setTitle(message)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        favoriteErrorListener.onDialogPositiveClick(FavoriteErrorDialog.this, isFavoriting, null);
                    }
                })
                .setNegativeButton("Not right now", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface FavoriteErrorListener {
        void onDialogPositiveClick(DialogFragment dialog, boolean isFavoriting, @Nullable NewsItem newsItem);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            favoriteErrorListener = (FavoriteErrorListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
}
