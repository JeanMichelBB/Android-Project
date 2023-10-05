package com.example.android_project.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_project.R;
import com.example.android_project.business.Authentication;
import com.example.android_project.business.UserManagement;
import com.example.android_project.data.ApplicationDB;
import com.example.android_project.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.time.LocalDate;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button btnLogin, btnNewUser;
    Authentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.TxtEmail);
        txtPassword = findViewById(R.id.TxtPassword);
        btnLogin = findViewById(R.id.BtnLogin);
        btnNewUser = findViewById(R.id.BtnNewUser);
        authentication = new Authentication();

        setupListeners();
    }

    private void login()
    {
        String email = txtEmail.getText().toString().trim();
        String pass = txtPassword.getText().toString().trim();

        if(email.isEmpty() || pass.isEmpty())
        {
            Toast.makeText(LoginActivity.this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
            return;
        }

        authentication.authenticate(LoginActivity.this, email, pass);
    }

    private void redirectToHome(UserModel user)
    {
        Intent intent = user.isAdministrator() ?
                new Intent(LoginActivity.this, HomeAdministratorActivity.class) :
                new Intent(LoginActivity.this, HomeUserActivity.class);

        intent.putExtra("AUTH_USER", user);
        startActivity(intent);
    }

    private void redirectToSignup()
    {
        Intent intent = new Intent(LoginActivity.this, NewUserActivity.class);
        startActivity(intent);
    }

    private void setupListeners()
    {
        authentication.onAuthenticationListener(new Authentication.AuthenticationListener() {
            @Override
            public void onLoginSuccess(String uId) {
                new UserManagement().onUserManagemenrListener(new UserManagement.UserManagementListener() {
                    @Override
                    public void onGetUserByIdSuccess(UserModel user) {
                        redirectToHome(user);
                    }

                    @Override
                    public void onGetUserByIdFail(String message) {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }).getUserById(uId);
            }

            @Override
            public void onLoginFail(String message) {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToSignup();
            }
        });
    }
}