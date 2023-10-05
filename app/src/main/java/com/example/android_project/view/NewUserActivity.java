package com.example.android_project.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_project.R;
import com.example.android_project.business.Authentication;
import com.example.android_project.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewUserActivity extends AppCompatActivity
{
    FirebaseAuth mAuth;
    EditText txtFirstName, txtLastName, txtDob, txtEmail, txtPassword, txtPasswordConfirm;
    Button btnCreateAccount, btnBackLogin;
    Calendar myCalendar;
    Authentication authentication;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        myCalendar = Calendar.getInstance();
        authentication = new Authentication();

        setupListeners();
    }

    private void updateDateOfBirth()
    {
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.CANADA);
        txtDob.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void tryCreateAccount()
    {

        try {
            String password = txtPassword.getText().toString().trim();
            String passwordConfirm = txtPasswordConfirm.getText().toString().trim();

            UserModel newUser = new UserModel(
                    txtFirstName.getText().toString().trim(),
                    txtLastName.getText().toString().trim(),
                    new Date(),
                    txtEmail.getText().toString().trim(),
                    txtPassword.getText().toString().trim(),
                    false
            );

            newUser.tryValidateModel();
            newUser.setDateOfBirth(tryGetDateOfBirth());

            if (!password.equals(passwordConfirm)) {
                Toast.makeText(NewUserActivity.this, "Password and password confirmation does not match.", Toast.LENGTH_SHORT).show();
                return;
            }

            authentication.createNewUser(NewUserActivity.this, newUser);
        }
        catch (IllegalArgumentException ex)
        {
            Toast.makeText(NewUserActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(NewUserActivity.this, "Something wrong is not right.", Toast.LENGTH_SHORT).show();
            Log.d("ERROR", ex.getMessage());
        }
    }

    private Date tryGetDateOfBirth()
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(txtDob.getText().toString());
        }
        catch (Exception _)
        {
            throw new IllegalArgumentException("Invalid Date of Birth.");
        }
    }

    private void setupListeners()
    {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateDateOfBirth();
            }
        };

        txtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewUserActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        authentication.onUserCreatedListener(new Authentication.UserCreatedListener()
        {
            @Override
            public void onCreateSuccess() {
                Toast.makeText(NewUserActivity.this, "User has been created.", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCreateFail(String message) {
                Toast.makeText(NewUserActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        btnBackLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                tryCreateAccount();
            }
        });
    }
}