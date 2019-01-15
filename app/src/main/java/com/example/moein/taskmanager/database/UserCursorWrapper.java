package com.example.moein.taskmanager.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.moein.taskmanager.models.Task;
import com.example.moein.taskmanager.models.User;

import java.util.Date;
import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser() {
        UUID uuid = UUID.fromString(getString(getColumnIndex(UserDbSchema.UserTable.Cols.UUID)));
        String userName = getString(getColumnIndex(UserDbSchema.UserTable.Cols.USERNAME));
        String password = getString(getColumnIndex(UserDbSchema.UserTable.Cols.PASSWORD));

        User user = new User(uuid);
        user.setUserName(userName);
        user.setPassword(password);

        return user;
    }
}
