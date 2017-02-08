package com.greenfox.fuchsit.zerdareader.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.greenfox.fuchsit.zerdareader.R;
import com.greenfox.fuchsit.zerdareader.dagger.DaggerMockServerComponent;
import com.greenfox.fuchsit.zerdareader.model.LoginRequest;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import javax.inject.Inject;

/**
 * Created by Anna on 17/02/06.
 */

public class SignupActivity extends AppCompatActivity {

    Button registerButton;
    EditText emailToReg, passwordToReg;
    TextInputLayout textInputLayout;

    @Inject
    ReaderApiInterface apiService;

    SharedPreferences loginData;

    LoginRequest loginRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        emailToReg = (EditText) findViewById(R.id.userName);
        passwordToReg = (EditText) findViewById(R.id.password);
        registerButton = (Button) findViewById(R.id.registerButton);
        textInputLayout = (TextInputLayout) findViewById(R.id.register_error_text);

        DaggerMockServerComponent.builder().build().inject(this);
    }

    public void register(View view) {

    }
}
