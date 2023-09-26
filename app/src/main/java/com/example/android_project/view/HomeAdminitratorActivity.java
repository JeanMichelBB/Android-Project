package com.example.android_project.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android_project.R;
import com.example.android_project.models.UserModel;

public class HomeAdminitratorActivity extends AppCompatActivity {

    UserModel administrator;
    TextView txtWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_adminitrator);

        administrator = (UserModel) getIntent().getSerializableExtra("AUTH_USER");

        txtWelcome = findViewById(R.id.txtWelcome);
        txtWelcome.setText("Welcome " + administrator.getFirstName() );
    }
}