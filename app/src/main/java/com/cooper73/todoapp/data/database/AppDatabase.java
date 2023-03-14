package com.cooper73.todoapp.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.cooper73.todoapp.data.converters.DateConverter;
import com.cooper73.todoapp.data.daos.TaskDao;
import com.cooper73.todoapp.data.daos.TaskListDao;
import com.cooper73.todoapp.data.daos.UserDao;
import com.cooper73.todoapp.data.entities.Task;
import com.cooper73.todoapp.data.entities.TaskList;
import com.cooper73.todoapp.data.entities.User;

@Database(entities = {User.class, TaskList.class, Task.class}, version = 4)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract TaskListDao taskListDao();
    public abstract TaskDao taskDao();
}
