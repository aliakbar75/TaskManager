package com.example.moein.taskmanager.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {

    @Id(autoincrement = true)
    private Long mId;

    @Unique
    private String mUserName;

    private String mPassword;

    @Generated(hash = 244301423)
    public User(Long mId, String mUserName, String mPassword) {
        this.mId = mId;
        this.mUserName = mUserName;
        this.mPassword = mPassword;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

    public String getMUserName() {
        return this.mUserName;
    }

    public void setMUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getMPassword() {
        return this.mPassword;
    }

    public void setMPassword(String mPassword) {
        this.mPassword = mPassword;
    }

//    public User() {
//        this(UUID.randomUUID());
//    }
//
//    public User(UUID id) {
//        mId = id;
//    }
//
//    public UUID getId() {
//        return mId;
//    }
//
//    public String getUserName() {
//        return mUserName;
//    }
//
//    public void setUserName(String userName) {
//        mUserName = userName;
//    }
//
//    public String getPassword() {
//        return mPassword;
//    }
//
//    public void setPassword(String password) {
//        mPassword = password;
//    }
}
