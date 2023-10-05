package com.example.android_project.business;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.android_project.data.ApplicationDB;
import com.example.android_project.models.UserModel;
import com.example.android_project.view.LoginActivity;
import com.example.android_project.view.NewUserActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.Executor;

public class Authentication {
    private FirebaseAuth mAuth;
    private AuthenticationListener authenticationListener;
    private UserCreatedListener userCreatedListener;

    public Authentication()
    {
        mAuth = FirebaseAuth.getInstance();
        this.authenticationListener = null;
        this.userCreatedListener = null;
    }

    public void onAuthenticationListener(AuthenticationListener listener)
    {
        this.authenticationListener = listener;
    }

    public void onUserCreatedListener(UserCreatedListener listener)
    {
        this.userCreatedListener = listener;
    }

    public void authenticate(Activity activity, String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(authenticationListener != null)
                            {
                                authenticationListener.onLoginSuccess(user.getUid());
                            }
                        } else {
                            if(authenticationListener != null)
                            {
                                authenticationListener.onLoginFail("Incorrect username or password.");
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("ERROR", e.getMessage());
                    }
                });
    }

    public void createNewUser(Activity activity, UserModel newUser)
    {
        mAuth.createUserWithEmailAndPassword(newUser.getEmail(), newUser.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            updateFirebaseUserInfo(newUser);
                        }
                        else
                        {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                if(userCreatedListener != null)
                                {
                                    userCreatedListener.onCreateFail("Email " + newUser.getEmail() + " is already registered.");
                                }
                            }
                            else
                            {
                                Log.e("ERROR", task.getException().getMessage());
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("ERROR", e.getMessage());
                    }
                });
    }

    public void updateFirebaseUserInfo(UserModel newUser)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(newUser.getFullName())
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            newUser.setUserId(user.getUid());
                            createUserDetails(newUser);

                            if(userCreatedListener != null)
                            {
                                userCreatedListener.onCreateSuccess();
                            }
                        }
                    }
                });
    }

    public void createUserDetails(UserModel newUser)
    {
        ApplicationDB db = new ApplicationDB();
        db.InsertNewUser(newUser);
    }

    public static void logout()
    {
        FirebaseAuth.getInstance().signOut();
    }

    public FirebaseUser getCurrentUser()
    {
        return mAuth.getCurrentUser();
    }

    public interface AuthenticationListener
    {
        public void onLoginSuccess(String uId);
        public void onLoginFail(String message);
    }

    public interface UserCreatedListener
    {
        public void onCreateSuccess();
        public void onCreateFail(String message);
    }

}
