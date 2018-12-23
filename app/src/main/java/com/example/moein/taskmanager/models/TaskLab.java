package com.example.moein.taskmanager.models;

import java.util.ArrayList;
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
        Task task = new Task("hello");
        mTasks.put(task.getId(),task);
    }

    public void addTask(){

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
}
