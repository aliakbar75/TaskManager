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

    public String addDescription(String description){
        return description;
    }

    public void addTask(String title, String description, Date date, Date time,int color, int iconColor){
        Task task = new Task(title);
        task.setDescriptions(description);
        task.setDate(date);
        task.setTime(time);
        task.setColor(color);
        task.setIconColor(iconColor);
        mTasks.put(task.getId(),task);
        mUndoneTasks.put(task.getId(),task);
    }

    public void editTask(Task task,String title, String description, Date date, Date time,int color, int iconColor){
        task.setTitle(title);
        task.setDescriptions(description);
        task.setDate(date);
        task.setTime(time);
        task.setColor(color);
        task.setIconColor(iconColor);
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

    public List<Task> getAllTasks() {
        return new ArrayList<>(mTasks.values());
    }

    public List<Task> getDoneTasks() {
        return new ArrayList<>(mDoneTasks.values());
    }

    public List<Task> getUndoneTasks() {
        return new ArrayList<>(mUndoneTasks.values());
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
