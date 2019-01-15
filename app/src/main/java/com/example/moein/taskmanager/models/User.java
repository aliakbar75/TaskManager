package com.example.moein.taskmanager.models;

import java.util.UUID;

public class User {

    private UUID mId;
    private String mUserName;
    private String mPassword;

    public User() {
        this(UUID.randomUUID());
    }

    public User(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
