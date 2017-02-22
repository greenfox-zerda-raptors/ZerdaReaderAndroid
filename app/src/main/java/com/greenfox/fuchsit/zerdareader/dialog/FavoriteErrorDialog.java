package com.greenfox.fuchsit.zerdareader.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.model.FavoriteHandler;

/**
 * Created by regnisalram on 2/9/17.
 */

public class FavoriteErrorDialog extends DialogFragment {

    String message;
    boolean isFavoriting;
    long itemId;

    FavoriteErrorListener favoriteErrorListener;

    public FavoriteErrorDialog(String message, boolean isFavoriting, long itemId) {
        this.message = message;
        this.isFavoriting = isFavoriting;
        this.itemId = itemId;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.dialog_favorite_error);
        builder.setTitle(message)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        favoriteErrorListener.onDialogPositiveClick(FavoriteErrorDialog.this, isFavoriting, itemId);
                    }
                })
                .setNegativeButton("Not right now", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    public interface FavoriteErrorListener {
        void onDialogPositiveClick(DialogFragment dialog, boolean isFavoriting, long itemId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            favoriteErrorListener = new FavoriteHandler((AppCompatActivity) context);
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
}
