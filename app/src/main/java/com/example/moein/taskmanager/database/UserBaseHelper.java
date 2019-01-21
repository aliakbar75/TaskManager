package com.example.moein.taskmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.moein.taskmanager.models.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class UserBaseHelper extends DaoMaster.DevOpenHelper {

    public UserBaseHelper(Context context, String name) {
        super(context, name);
    }

    public UserBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
