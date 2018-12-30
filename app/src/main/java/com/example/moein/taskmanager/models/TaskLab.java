package com.example.moein.taskmanager.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class TaskLab {

    private static TaskLab instance;
    private LinkedHashMap<UUID,Task> mTasks;
    private LinkedHashMap<UUID,Task> mDoneTasks;
    private LinkedHashMap<UUID,Task> mUndoneTasks;

    private TaskLab() {
        mTasks = new LinkedHashMap<>();
        mDoneTasks = new LinkedHashMap<>();
        mUndoneTasks = new LinkedHashMap<>();
    }

    public void addTask(Task task){
        mTasks.put(task.getId(),task);
        mUndoneTasks.put(task.getId(),task);
    }

    public void editTask(Task task){
        mTasks.put(task.getId(),task);
    }

    public void taskDone(Task task){
        task.setDone(true);
        mDoneTasks.put(task.getId(),task);
        mUndoneTasks.remove(task.getId());
    }

    public void deleteTask(Task task){
        mTasks.remove(task.getId());
        mDoneTasks.remove(task.getId());
        mUndoneTasks.remove(task.getId());
    }

    public LinkedHashMap<UUID,Task> getAllTasks() {
        return mTasks;
    }

    public LinkedHashMap<UUID,Task> getDoneTasks() {
        return mDoneTasks;
    }

    public LinkedHashMap<UUID,Task> getUndoneTasks() {
        return mUndoneTasks;
    }

    public static TaskLab getInstance(){
        if (instance == null)
            instance = new TaskLab();
        return instance;
    }

    public Task getTask(UUID id){
        return mTasks.get(id);
    }
}
