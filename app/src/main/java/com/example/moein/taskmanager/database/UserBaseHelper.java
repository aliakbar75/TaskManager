package com.example.moein.taskmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class UserBaseHelper extends SQLiteOpenHelper {
    public UserBaseHelper(Context context) {
        super(context, UserDbSchema.NAME, null, UserDbSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserDbSchema.UserTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                UserDbSchema.UserTable.Cols.UUID + ", " +
                UserDbSchema.UserTable.Cols.USERNAME + ", " +
                UserDbSchema.UserTable.Cols.PASSWORD +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
