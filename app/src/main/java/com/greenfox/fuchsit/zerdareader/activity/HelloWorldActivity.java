package com.greenfox.fuchsit.zerdareader.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.R;

public class HelloWorldActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String userName = "nameKey";
    public static final String password = "passKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world);

        button = (Button) findViewById(R.id.button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView = (TextView) findViewById(R.id.text_view);
                textView.setText("Hello Fuchsit!");
            }
        });
    }
}
