package com.example.android_project.models;

import java.io.Serializable;
import java.util.Date;


public class UserModel implements Serializable {

    private String UserId;
    private String FirstName;
    private String LastName;
    private Date DateOfBirth;
    private String Email;
    private String Password;
    private boolean IsAdministrator;

    public UserModel()
    {
    }

    public UserModel(String firstName, String lastName, Date dateOfBirth, String email, String password, boolean isAdministrator)
    {
        FirstName = firstName;
        LastName = lastName;
        DateOfBirth = dateOfBirth;
        Email = email;
        Password = password;
        IsAdministrator = isAdministrator;
    }
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
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

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    public String getFullName()
    {
        return this.FirstName + " " + this.LastName;
    }

    public void tryValidateModel()
    {
        if(this.FirstName.isEmpty())
            throw new IllegalArgumentException("Invalid first name.");

        if(this.LastName.isEmpty())
            throw new IllegalArgumentException("Invalid last name.");

        if(this.DateOfBirth.toString().isEmpty())
            throw new IllegalArgumentException("Invalid date ff birth.");

        if(this.Email.isEmpty())
            throw new IllegalArgumentException("Invalid email address.");

        if(this.Password.isEmpty())
            throw new IllegalArgumentException("Invalid password.");

    }

}
