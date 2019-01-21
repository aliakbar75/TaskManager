package com.example.moein.taskmanager.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.moein.taskmanager.database.TaskCursorWrapper;
import com.example.moein.taskmanager.database.TaskDbSchema;
import com.example.moein.taskmanager.database.UserBaseHelper;
import com.example.moein.taskmanager.database.UserCursorWrapper;
import com.example.moein.taskmanager.database.UserDbSchema;

import org.greenrobot.greendao.database.Database;

import java.util.List;
import java.util.UUID;

public class UserLab {

    private static UserLab instance;
    private DaoSession mDaoSession;
    private Database mDatabase;
    private UserDao mUserDao;
//    private SQLiteDatabase mDatabase;
//    private Context mContext;

    private UserLab(Context context) {

        context = context.getApplicationContext();
        UserBaseHelper userBaseHelper = new UserBaseHelper(context,"tasks.db");
        mDatabase = userBaseHelper.getWritableDb();

        mDaoSession = new DaoMaster(mDatabase).newSession();

        mUserDao = mDaoSession.getUserDao();
//        mContext = context.getApplicationContext();
//        mDatabase = new UserBaseHelper(mContext).getWritableDatabase();
    }

    public static UserLab getInstance(Context context){
        if (instance == null)
            instance = new UserLab(context);
        return instance;
    }

    public void addUser(User user){

        mUserDao.insert(user);
//        ContentValues values = getContentValues(user);
//        mDatabase.insert(UserDbSchema.UserTable.NAME, null, values);
    }

    public void updateUser(User user){
        mUserDao.update(user);
    }

    public User getUser(String userName){

        User user = mUserDao.queryBuilder()
                .where(UserDao.Properties.MUserName.eq(userName))
                .unique();

        return user;
//        String whereClause = UserDbSchema.UserTable.Cols.USERNAME + " = ?";
//        String[] whereArgs = new String[]{userName};
//
//        UserCursorWrapper userCursorWrapper = queryUser(whereClause, whereArgs);
//
//        try {
//            if (userCursorWrapper.getCount() == 0)
//                return null;
//
//            userCursorWrapper.moveToFirst();
//            return userCursorWrapper.getUser();
//        } finally {
//            userCursorWrapper.close();
//        }
    }

    public User getUser(Long userId){

        return mUserDao.load(userId);
//        String whereClause = UserDbSchema.UserTable.Cols.UUID + " = ?";
//        String[] whereArgs = new String[]{userId.toString()};
//
//        UserCursorWrapper userCursorWrapper = queryUser(whereClause, whereArgs);
//
//        try {
//            if (userCursorWrapper.getCount() == 0)
//                return null;
//
//            userCursorWrapper.moveToFirst();
//            return userCursorWrapper.getUser();
//        } finally {
//            userCursorWrapper.close();
//        }
    }



//    private UserCursorWrapper queryUser(String whereClause, String[] whereArgs) {
//        Cursor cursor = mDatabase.query(
//                UserDbSchema.UserTable.NAME,
//                null,
//                whereClause,
//                whereArgs,
//                null,
//                null,
//                null);
//
//        return new UserCursorWrapper(cursor);
//    }

//    public ContentValues getContentValues(User user) {
//        ContentValues values = new ContentValues();
//        values.put(UserDbSchema.UserTable.Cols.UUID, user.getId().toString());
//        values.put(UserDbSchema.UserTable.Cols.USERNAME, user.getUserName());
//        values.put(UserDbSchema.UserTable.Cols.PASSWORD, user.getPassword());
//
//        return values;
//    }
}
