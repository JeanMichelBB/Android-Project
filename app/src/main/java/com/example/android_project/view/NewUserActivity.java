package com.example.android_project.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_project.R;

public class NewUserActivity extends AppCompatActivity {

    EditText txtFirstName, txtLastName, txtDob, txtEmail, txtPassword, txtPasswordConfirm;
    Button btnCreateAccount, btnBackLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        txtFirstName = findViewById(R.id.TxtFirstName);
        txtLastName = findViewById(R.id.TxtLastName);
        txtDob = findViewById(R.id.TxtDob);
        txtEmail = findViewById(R.id.TxtEmail);
        txtPassword = findViewById(R.id.TxtPassword);
        txtPasswordConfirm = findViewById(R.id.TxtPasswordConfirm);
        btnCreateAccount = findViewById(R.id.BtnCreateAccount);
        btnBackLogin = findViewById(R.id.BtnBackLogin);

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}