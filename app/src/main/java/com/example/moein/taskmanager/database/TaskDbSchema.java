package com.example.moein.taskmanager.database;

public class TaskDbSchema {

    public static final String NAME = "tasks.db";
    public static final int VERSION = 1;

    public static final class TaskTable {
        public static final String NAME = "task";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DESCRIPTIONS = "descriptions";
            public static final String DATE = "date";
            public static final String TIME = "time";
            public static final String DONE = "done";
            public static final String COLOR = "color";
            public static final String ICON_COLOR = "icon_color";
            public static final String USER_ID = "user_id";
        }
    }
}
