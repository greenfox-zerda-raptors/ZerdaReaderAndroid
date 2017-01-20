package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.R;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText editUserName, editPassword;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editUserName = (EditText) findViewById(R.id.userName);
        editPassword = (EditText) findViewById(R.id.password);

        button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = editUserName.getText().toString();
                String password = editPassword.getText().toString();
                loginUser(username, password);
            }
        });
    }
}
