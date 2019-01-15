package com.example.moein.taskmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class TaskBaseHelper extends SQLiteOpenHelper {
    public TaskBaseHelper(Context context) {
        super(context, TaskDbSchema.NAME, null, TaskDbSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TaskDbSchema.TaskTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                TaskDbSchema.TaskTable.Cols.UUID + ", " +
                TaskDbSchema.TaskTable.Cols.TITLE + ", " +
                TaskDbSchema.TaskTable.Cols.DESCRIPTIONS + ", " +
                TaskDbSchema.TaskTable.Cols.DATE + ", " +
                TaskDbSchema.TaskTable.Cols.TIME + ", " +
                TaskDbSchema.TaskTable.Cols.DONE + ", " +
                TaskDbSchema.TaskTable.Cols.COLOR + ", " +
                TaskDbSchema.TaskTable.Cols.ICON_COLOR + ", " +
                TaskDbSchema.TaskTable.Cols.USER_ID +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
