package com.example.moein.taskmanager.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.moein.taskmanager.models.Task;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

//    public Task getTask() {
//        UUID uuid = UUID.fromString(getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.UUID)));
//        String title = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.TITLE));
//        String descriptions = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.DESCRIPTIONS));
//        Date date = new Date(getLong(getColumnIndex(TaskDbSchema.TaskTable.Cols.DATE)));
//        Date time = new Date(getLong(getColumnIndex(TaskDbSchema.TaskTable.Cols.TIME)));
//        boolean isDone = getInt(getColumnIndex(TaskDbSchema.TaskTable.Cols.DONE)) != 0;
//        int color = getInt(getColumnIndex(TaskDbSchema.TaskTable.Cols.COLOR));
//        int iconColor = getInt(getColumnIndex(TaskDbSchema.TaskTable.Cols.ICON_COLOR));
//        UUID userId = UUID.fromString(getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.USER_ID)));
//
//        Task task = new Task(title,uuid);
//        task.setTitle(title);
//        task.setDescriptions(descriptions);
//        task.setDate(date);
//        task.setTime(time);
//        task.setDone(isDone);
//        task.setColor(color);
//        task.setIconColor(iconColor);
//        task.setUserId(userId);
//
//        return task;
//    }
}
