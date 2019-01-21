package com.example.moein.taskmanager.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class Task {

    @Id(autoincrement = true)
    private Long mId;

    private String mTitle;
    private String mDescriptions;
    private Date mDate;
    private Date mTime;
    private boolean mDone;
    private int mColor;
    private int mIconColor;

    private Long mUserId;

    @ToOne(joinProperty = "mUserId")
    private User mUser;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;

    @Generated(hash = 229311900)
    public Task(Long mId, String mTitle, String mDescriptions, Date mDate,
            Date mTime, boolean mDone, int mColor, int mIconColor, Long mUserId) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mDescriptions = mDescriptions;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mDone = mDone;
        this.mColor = mColor;
        this.mIconColor = mIconColor;
        this.mUserId = mUserId;
    }

    @Generated(hash = 733837707)
    public Task() {
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

    public String getMTitle() {
        return this.mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMDescriptions() {
        return this.mDescriptions;
    }

    public void setMDescriptions(String mDescriptions) {
        this.mDescriptions = mDescriptions;
    }

    public Date getMDate() {
        return this.mDate;
    }

    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }

    public Date getMTime() {
        return this.mTime;
    }

    public void setMTime(Date mTime) {
        this.mTime = mTime;
    }

    public boolean getMDone() {
        return this.mDone;
    }

    public void setMDone(boolean mDone) {
        this.mDone = mDone;
    }

    public int getMColor() {
        return this.mColor;
    }

    public void setMColor(int mColor) {
        this.mColor = mColor;
    }

    public int getMIconColor() {
        return this.mIconColor;
    }

    public void setMIconColor(int mIconColor) {
        this.mIconColor = mIconColor;
    }

    public Long getMUserId() {
        return this.mUserId;
    }

    public void setMUserId(Long mUserId) {
        this.mUserId = mUserId;
    }

    @Generated(hash = 1377221062)
    private transient Long mUser__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 747440188)
    public User getMUser() {
        Long __key = this.mUserId;
        if (mUser__resolvedKey == null || !mUser__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User mUserNew = targetDao.load(__key);
            synchronized (this) {
                mUser = mUserNew;
                mUser__resolvedKey = __key;
            }
        }
        return mUser;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 250977017)
    public void setMUser(User mUser) {
        synchronized (this) {
            this.mUser = mUser;
            mUserId = mUser == null ? null : mUser.getMId();
            mUser__resolvedKey = mUserId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }

//    public Task(String title) {
//        this(title,UUID.randomUUID());
//    }
//
//    public Task(String title,UUID id) {
//        mId = id;
//        mTitle = title;
//        mDone = false;
//        mDate = new Date();
//    }
//
//    public UUID getUserId() {
//        return mUserId;
//    }
//
//    public void setUserId(UUID userId) {
//        mUserId = userId;
//    }
//
//    public int getIconColor() {
//        return mIconColor;
//    }
//
//    public void setIconColor(int iconColor) {
//        mIconColor = iconColor;
//    }
//
//    public int getColor() {
//        return mColor;
//    }
//
//    public void setColor(int color) {
//        mColor = color;
//    }
//
//    public UUID getId() {
//        return mId;
//    }
//
//    public String getTitle() {
//        return mTitle;
//    }
//
//    public void setTitle(String title) {
//        mTitle = title;
//    }
//
//    public String getDescriptions() {
//        return mDescriptions;
//    }
//
//    public void setDescriptions(String descriptions) {
//        mDescriptions = descriptions;
//    }
//
//    public Date getDate() {
//        return mDate;
//    }
//
//    public void setDate(Date date) {
//        mDate = date;
//    }
//
//    public Date getTime() {
//        return mTime;
//    }
//
//    public void setTime(Date time) {
//        mTime = time;
//    }
//
//    public boolean isDone() {
//        return mDone;
//    }
//
//    public void setDone(boolean done) {
//        mDone = done;
//    }
}
