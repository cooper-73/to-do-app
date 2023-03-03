package com.cooper73.todoapp.data.database;

import android.content.Context;

import androidx.room.Room;

import com.cooper73.todoapp.data.daos.TaskListDao;

public class DBConnector {

    private static volatile DBConnector instance = null;
    private AppDatabase db;


    private DBConnector() {
    }

    private DBConnector(Context context) {
        this.db = Room
                .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "to-do-app-db")
                .build();
    }

    public static DBConnector getInstance(Context context) {
        if (instance == null) {
            synchronized (DBConnector.class) {
                if (instance == null) {
                    instance = new DBConnector(context);
                }
            }
        }

        return instance;
    }

    public TaskListDao getTaskListDao() {
        return db.taskListDao();
    }
}
