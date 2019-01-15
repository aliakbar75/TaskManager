package com.example.moein.taskmanager.models;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mTitle;
    private String mDescriptions;
    private Date mDate;
    private Date mTime;
    private boolean mDone;
    private int mColor;
    private int mIconColor;
    private UUID mUserId;

    public Task(String title) {
        this(title,UUID.randomUUID());
    }

    public Task(String title,UUID id) {
        mId = id;
        mTitle = title;
        mDone = false;
        mDate = new Date();
    }

    public UUID getUserId() {
        return mUserId;
    }

    public void setUserId(UUID userId) {
        mUserId = userId;
    }

    public int getIconColor() {
        return mIconColor;
    }

    public void setIconColor(int iconColor) {
        mIconColor = iconColor;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescriptions() {
        return mDescriptions;
    }

    public void setDescriptions(String descriptions) {
        mDescriptions = descriptions;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }
}
