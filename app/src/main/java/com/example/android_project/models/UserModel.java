package com.example.android_project.models;

import java.io.Serializable;
import java.time.LocalDate;


public class UserModel implements Serializable {

    private static int Id = 1;

    private int UserId;
    private String Login;
    private String FirstName;
    private String LastName;
    private LocalDate DateOfBirth;
    private String Email;
    private String Password;
    private boolean IsAdministrator;

    public UserModel(String login, String firstName, String lastName, LocalDate dateOfBirth, String email, String password, boolean isAdministrator) {

        Login = login;
        FirstName = firstName;
        LastName = lastName;
        DateOfBirth = dateOfBirth;
        Email = email;
        Password = password;
        IsAdministrator = isAdministrator;
        this.UserId = UserModel.Id++;
    }
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isAdministrator() {
        return IsAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        IsAdministrator = administrator;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        this.Login = login;
    }
}
