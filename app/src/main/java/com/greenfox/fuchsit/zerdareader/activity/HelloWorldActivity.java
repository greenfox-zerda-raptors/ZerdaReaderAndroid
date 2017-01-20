package com.greenfox.fuchsit.zerdareader.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.greenfox.fuchsit.zerdareader.R;

public class HelloWorldActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView = (TextView) findViewById(R.id.text_view);
                textView.setText("Hello Fuchsit!");
            }
        });
    }
}
