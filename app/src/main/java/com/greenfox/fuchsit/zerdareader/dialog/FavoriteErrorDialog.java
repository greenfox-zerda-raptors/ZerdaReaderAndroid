package com.greenfox.fuchsit.zerdareader.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.greenfox.fuchsit.zerdareader.R;

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
                        favoriteErrorListener.onDialogPositiveClick(FavoriteErrorDialog.this, isFavoriting);
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
        void onDialogPositiveClick(DialogFragment dialog, boolean isFavoriting);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            favoriteErrorListener = (FavoriteErrorListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }
}
