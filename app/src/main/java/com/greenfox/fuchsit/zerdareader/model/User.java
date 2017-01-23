package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Anna on 17/01/16.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

}
