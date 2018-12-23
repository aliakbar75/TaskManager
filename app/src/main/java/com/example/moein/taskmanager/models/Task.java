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
}
