package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.data.entities.Task;

import java.util.List;

public interface TaskListInteractor {
    void renameTaskList(String taskListId, String newTitle);
    void deleteTaskList(String taskListId);
    void getToDoTasks(String taskListId);
    void getCompletedTasks(String taskListId);
    void updateTaskCompletedFlag(String taskId, boolean isCompleted, int position);
    void addTask(String taskListId, String title);

    interface Callbacks {
        void successRenameTaskList(String newTitle);
        void successDeleteTaskList();
        void successGetToDoTasks(List<Task> toDoTasks);
        void successCompletedTasks(List<Task> completedTasks);
        void successUpdateTaskCompletedFlag(String taskId, int position);
        void successAddTask(Task newTask);
    }
}
