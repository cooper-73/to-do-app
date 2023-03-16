package com.cooper73.todoapp.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cooper73.todoapp.data.entities.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    Task getById(String taskId);

    @Query("SELECT * FROM tasks WHERE task_list_id = :taskListId AND is_completed = 0")
    List<Task> getAllToDoByTaskListId(String taskListId);

    @Query("SELECT * FROM tasks WHERE task_list_id = :taskListId AND is_completed = 1")
    List<Task> getAllCompletedByTaskListId(String taskListId);

    @Query("SELECT tasks.* FROM tasks LEFT JOIN task_lists ON tasks.task_list_id = task_lists.id" +
            " LEFT JOIN users ON task_lists.user_id = users.id WHERE users.id = :userId AND tasks.is_important = 1")
    List<Task> getAllImportantTasksByUserId(String userId);

    @Insert
    void insertNewTask(Task task);

    @Update
    void updateTask(Task task);
}
