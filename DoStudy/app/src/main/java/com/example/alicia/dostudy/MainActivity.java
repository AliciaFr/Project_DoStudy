package com.example.alicia.dostudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;
    private EditText inputEmail, inputPassword;
    private View viewEmail, viewPassword;
    private CheckBox checkBoxRemember;
    private Button buttonLogin, buttonSignup;
    private TextView forgotPassword;

    private String email, password;

    private DatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initalizeUIComponents();
    }

    private void initalizeUIComponents() {
        logo = (ImageView) findViewById(R.id.login_logo);
        inputEmail = (EditText) findViewById(R.id.login_input_email);
        inputPassword = (EditText) findViewById(R.id.login_input_password);
        viewEmail = findViewById(R.id.login_view_email);
        viewPassword = findViewById(R.id.login_view_password);
        checkBoxRemember = (CheckBox) findViewById(R.id.login_remember);
        buttonLogin = (Button) findViewById(R.id.login_button);
        buttonSignup = (Button) findViewById(R.id.login_signup_button);
        forgotPassword = (TextView) findViewById(R.id.login_forgot);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
        checkPassword();
    }

    private void checkPassword() {
        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();
        String check = helper.searchPassword(email);
        if (password.equals(check)) {
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    i.putExtra("Email", email);
                    startActivity(i);
                }
            });
        } else {
            Toast password = Toast.makeText(MainActivity.this, "Email and password don't match", Toast.LENGTH_SHORT);
            password.show();
        }

    }
}