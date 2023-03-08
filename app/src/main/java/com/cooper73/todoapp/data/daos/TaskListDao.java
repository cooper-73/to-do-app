package com.cooper73.todoapp.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cooper73.todoapp.data.entities.TaskList;

import java.util.List;

@Dao
public interface TaskListDao {
    @Query("SELECT * FROM task_lists WHERE user_id = :userId")
    List<TaskList> getAllByUserId(String userId);

    @Insert
    void insertNewTaskList(TaskList taskList);

    @Query("SELECT * FROM task_lists WHERE id = :taskListId")
    TaskList getById(String taskListId);

    @Update
    void updateTaskList(TaskList taskList);

    @Delete
    void deleteTaskList(TaskList taskList);
}
