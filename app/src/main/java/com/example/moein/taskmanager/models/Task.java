package com.example.moein.taskmanager.models;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mTitle;
    private String mDescriptions;
    private Date mDate;
    private Time mTime;
    private boolean isDone;

    public UUID getId() {
        return mId;
    }

    public Task(String title) {
        mId = UUID.randomUUID();
        mTitle = title;
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

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time time) {
        mTime = time;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
