package com.example.moein.taskmanager.models;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mTitle;
    private String mDescriptions;
    private Date mDate;
    private Date mTime;
    private boolean isDone;
    private int mColor;

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public UUID getId() {
        return mId;
    }

    public Task(String title) {
        mId = UUID.randomUUID();
        mTitle = title;
        isDone = false;
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
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
