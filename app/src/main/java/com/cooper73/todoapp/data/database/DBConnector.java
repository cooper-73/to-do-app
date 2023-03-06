package com.cooper73.todoapp.data.database;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBConnector {

    private static volatile DBConnector instance = null;
    private AppDatabase db;
    private ExecutorService executorService;
    Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    private DBConnector() {
    }

    private DBConnector(Context context) {
        this.db = Room
                .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "to-do-app-db")
                .fallbackToDestructiveMigration()
                .build();
        this.executorService = Executors.newFixedThreadPool(4);
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

    public AppDatabase getDatabase() {
        return db;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public Handler getMainThreadHandler() {
        return mainThreadHandler;
    }
}
