package com.example.moein.taskmanager.models;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.moein.taskmanager.database.TaskBaseHelper;
import com.example.moein.taskmanager.database.TaskCursorWrapper;
import com.example.moein.taskmanager.database.TaskDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskLab {

    private static TaskLab instance;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    private TaskLab(Context context) {

        mContext = context.getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();
    }

    public static TaskLab getInstance(Context context){
        if (instance == null)
            instance = new TaskLab(context);
        return instance;
    }

    public void addTask(Task task){
        ContentValues values = getContentValues(task);
        mDatabase.insert(TaskDbSchema.TaskTable.NAME, null, values);
    }

    public void updateTask(Task task) {
        ContentValues values = getContentValues(task);
        String whereClause = TaskDbSchema.TaskTable.Cols.UUID + " = ? ";
        mDatabase.update(TaskDbSchema.TaskTable.NAME, values,
                whereClause, new String[]{task.getId().toString()});
    }

    public void deleteTask(Task task){
        String whereClause = TaskDbSchema.TaskTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDatabase.delete(TaskDbSchema.TaskTable.NAME,whereClause,whereArgs);
    }

    public List<Task> getTasks(UUID userId,int done) {
        List<Task> tasks = new ArrayList<>();
        String whereClause = null;
        String[] whereArgs = null;
        if (done == 0){
            whereClause = TaskDbSchema.TaskTable.Cols.USER_ID + " = ?";
            whereArgs = new String[]{userId.toString()};
        }else if (done == 1){
            whereClause = TaskDbSchema.TaskTable.Cols.USER_ID + " = ? AND " + TaskDbSchema.TaskTable.Cols.DONE + " = 1";
            whereArgs = new String[]{userId.toString()};
        }else if (done == 2){
            whereClause = TaskDbSchema.TaskTable.Cols.USER_ID + " = ? AND " + TaskDbSchema.TaskTable.Cols.DONE + " = 0";
            whereArgs = new String[]{userId.toString()};
        }

        TaskCursorWrapper taskCursorWrapper = queryTask(whereClause, whereArgs);

        try {
            if (taskCursorWrapper.getCount() == 0)
                return tasks;

            taskCursorWrapper.moveToFirst();
            while (!taskCursorWrapper.isAfterLast()) {
                tasks.add(taskCursorWrapper.getTask());
                taskCursorWrapper.moveToNext();
            }
        } finally {
            taskCursorWrapper.close();
        }

        return tasks;
    }

    public Task getTask(UUID id){
        String whereClause = TaskDbSchema.TaskTable.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{id.toString()};

        TaskCursorWrapper taskCursorWrapper = queryTask(whereClause, whereArgs);

        try {
            if (taskCursorWrapper.getCount() == 0)
                return null;

            taskCursorWrapper.moveToFirst();
            return taskCursorWrapper.getTask();
        } finally {
            taskCursorWrapper.close();
        }
    }

    private TaskCursorWrapper queryTask(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TaskDbSchema.TaskTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        return new TaskCursorWrapper(cursor);
    }

    public ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.TaskTable.Cols.UUID, task.getId().toString());
        values.put(TaskDbSchema.TaskTable.Cols.TITLE, task.getTitle());
        values.put(TaskDbSchema.TaskTable.Cols.DESCRIPTIONS, task.getDescriptions());
        try{
            values.put(TaskDbSchema.TaskTable.Cols.DATE, task.getDate().getTime());
            values.put(TaskDbSchema.TaskTable.Cols.TIME, task.getTime().getTime());
        }catch (Exception e){

        }

        values.put(TaskDbSchema.TaskTable.Cols.DONE, task.isDone() ? 1 : 0);
        values.put(TaskDbSchema.TaskTable.Cols.COLOR, task.getColor());
        values.put(TaskDbSchema.TaskTable.Cols.ICON_COLOR, task.getIconColor());
        values.put(TaskDbSchema.TaskTable.Cols.USER_ID, task.getUserId().toString());

        return values;
    }
}
