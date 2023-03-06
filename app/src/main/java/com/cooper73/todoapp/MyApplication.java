package com.cooper73.todoapp;

import android.app.Application;
import android.os.Handler;

import com.cooper73.todoapp.data.database.AppDatabase;
import com.cooper73.todoapp.data.database.DBConnector;

import java.util.concurrent.ExecutorService;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBConnector.getInstance(this);
    }

    public static AppDatabase getDatabase() {
        return DBConnector.getInstance(null).getDatabase();
    }

    public static ExecutorService getExecutorService() {
        return DBConnector.getInstance(null).getExecutorService();
    }

    public static Handler getMainThreadHandler() {
        return DBConnector.getInstance(null).getMainThreadHandler();
    }
}
