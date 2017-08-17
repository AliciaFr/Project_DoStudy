package com.example.alicia.dostudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Alicia on 12.08.2017.
 */

public class SignupActivity extends AppCompatActivity {


    private TextView welcomeText, policy;
    private EditText inputEmail, inputName, inputPassword, inputCheckPassword;
    private Button signupButton;
    private String email, name, password, passwordCheck;

    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeUIComponents();
    }

    private void initializeUIComponents() {
        welcomeText = (TextView) findViewById(R.id.signup_welcome);
        policy = (TextView) findViewById(R.id.signup_policy);

        inputEmail = (EditText) findViewById(R.id.signup_input_email);
        inputName = (EditText) findViewById(R.id.signup_input_name);
        inputPassword = (EditText) findViewById(R.id.login_input_password);
        inputCheckPassword = (EditText) findViewById(R.id.signup_check_password);

        signupButton = (Button) findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}
