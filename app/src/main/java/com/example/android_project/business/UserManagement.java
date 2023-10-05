package com.example.android_project.business;

import com.example.android_project.data.ApplicationDB;
import com.example.android_project.models.UserModel;

public class UserManagement {
    private UserManagementListener userManagementListener;
    ApplicationDB applicationDB;

    public UserManagement()
    {
        this.userManagementListener = null;
        this.applicationDB = new ApplicationDB();
    }

    public void getUserById(String uId)
    {
        this.applicationDB.onUserDbListener(new ApplicationDB.UserDbListener() {
            @Override
            public void onGetUserByIdSuccess(UserModel model) {
                if(userManagementListener != null)
                {
                    userManagementListener.onGetUserByIdSuccess(model);
                }
            }
            @Override
            public void onGetUserByIdFail(String message) {
                if(userManagementListener != null)
                {
                    userManagementListener.onGetUserByIdFail(message);
                }
            }
        }).getUserById(uId);
    }

    public UserManagement onUserManagemenrListener(UserManagementListener userManagementListener)
    {
        this.userManagementListener = userManagementListener;
        return this;
    }

    public interface UserManagementListener
    {
        public void onGetUserByIdSuccess(UserModel user);
        public void onGetUserByIdFail(String message);
    }
}
