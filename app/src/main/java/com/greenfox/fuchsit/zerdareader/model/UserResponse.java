package com.greenfox.fuchsit.zerdareader.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Zsuzska on 2017. 01. 19..
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("result")
    private boolean success;
    @SerializedName("token")
    private String token;
    
}
