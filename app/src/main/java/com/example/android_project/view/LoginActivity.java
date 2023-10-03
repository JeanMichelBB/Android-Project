package com.example.android_project.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_project.R;
import com.example.android_project.business.Authentication;
import com.example.android_project.data.ApplicationDB;
import com.example.android_project.models.UserModel;

import java.time.LocalDate;

public class LoginActivity extends AppCompatActivity {

    EditText txtLogin, txtPassword;
    Button btnLogin, btnNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLogin = findViewById(R.id.TxtLogin);
        txtPassword = findViewById(R.id.TxtPassword);
        btnLogin = findViewById(R.id.BtnLogin);
        btnNewUser = findViewById(R.id.BtnNewUser);

        loadInitialCatalog();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String login = txtLogin.getText().toString().trim();
                String pass = txtPassword.getText().toString().trim();

                UserModel authenticatedUser = Authentication.TryAuthenticateUser(login, pass);
                if(authenticatedUser != null)
                {
                    Intent intent = authenticatedUser.isAdministrator() ?
                            new Intent(LoginActivity.this, HomeAdministratorActivity.class) :
                            new Intent(LoginActivity.this, HomeUserActivity.class);

                    intent.putExtra("AUTH_USER", authenticatedUser);
                    startActivity(intent);

                    return;
                }

                Toast.makeText(LoginActivity.this, "Email or password is incorrect.", Toast.LENGTH_SHORT).show();
            }
        });

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, NewUserActivity.class);
                startActivity(intent);
            }
        });

    }


    private void loadInitialCatalog()
    {
        try {

            ApplicationDB.AddNewUser(new UserModel("admin", "Administrator", "Adm", LocalDate.parse("2000-01-01"), "admin@gmail.com", "admin", true));
            ApplicationDB.AddNewUser(new UserModel("thiago","Thiago", "LastName", LocalDate.parse("2000-01-01"), "thiago@gmail.com", "user", false));
            ApplicationDB.AddNewUser(new UserModel("jean","Jean", "LastName", LocalDate.parse("2000-01-01"), "jean@gmail.com", "user", false));
            ApplicationDB.AddNewUser(new UserModel("any","Any", "LastName", LocalDate.parse("2000-01-01"), "any@gmail.com", "user", false));

        } catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}