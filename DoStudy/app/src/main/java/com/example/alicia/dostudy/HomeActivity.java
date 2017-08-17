package com.example.alicia.dostudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Alicia on 12.08.2017.
 */

public class HomeActivity extends AppCompatActivity {

    private TextView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String email = getIntent().getStringExtra("Email");
        home = (TextView) findViewById(R.id.home_home);
        home.setText(email);


    }
}
