package com.cooper73.todoapp.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cooper73.todoapp.data.entities.TaskList;

import java.util.List;

@Dao
public interface TaskListDao {
    @Query("SELECT * FROM task_lists")
    List<TaskList> getAll();

    @Insert
    void insertNewTaskList(TaskList taskList);
}
