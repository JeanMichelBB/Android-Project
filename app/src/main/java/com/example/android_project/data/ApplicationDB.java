package com.example.android_project.data;

import android.content.OperationApplicationException;

import com.example.android_project.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDB {

        //TODO: This class must be replaced by a real Database.

        private static List<UserModel> userList = new ArrayList<UserModel>();

        public static void AddNewUser(UserModel newUser) throws OperationApplicationException {
                UserModel existingUser = FindUserByLogin(newUser.getLogin());
                if(existingUser != null)
                {
                        throw new OperationApplicationException("User " + newUser.getFirstName() + " is already registered.");
                }

                userList.add(newUser);
        }

        public static UserModel FindUserById(int userId)
        {
                return ApplicationDB.userList.stream().filter(u -> u.getUserId() == userId).findAny().orElse(null);
        }

        public static UserModel FindUserByLogin(String login)
        {
                return ApplicationDB.userList.stream().filter(u -> u.getLogin().toUpperCase().equals(login.toUpperCase())).findAny().orElse(null);
        }

}
