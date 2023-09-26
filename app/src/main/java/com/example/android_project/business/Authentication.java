package com.example.android_project.business;

import com.example.android_project.data.ApplicationDB;
import com.example.android_project.models.UserModel;

public class Authentication {

    public static UserModel TryAuthenticateUser(String login, String password)
    {
        UserModel user = ApplicationDB.FindUserByLogin(login);

        if(user == null || !user.getPassword().equals(password))
        {
            return null;
        }
        return user;
    }

}
