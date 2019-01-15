package com.example.moein.taskmanager.database;

public class UserDbSchema {

    public static final String NAME = "users.db";
    public static final int VERSION = 1;

    public static final class UserTable {
        public static final String NAME = "user";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";

        }
    }
}
