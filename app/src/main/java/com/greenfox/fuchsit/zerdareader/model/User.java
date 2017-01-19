package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anna on 17/01/16.
 */

public class User {

    private int id;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
}
