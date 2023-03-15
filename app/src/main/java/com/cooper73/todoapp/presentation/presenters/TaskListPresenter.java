package com.cooper73.todoapp.presentation.presenters;

public interface TaskListPresenter {
    void renameTaskList(String taskListId, String newTitle);
    void deleteTaskList(String taskListId);
    void loadToDoTasks(String taskListId);
    void loadCompletedTasks(String taskListId);
    void updateTaskCompletedFlag(String taskId, boolean isCompleted, int position);
    void updateTaskImportantFlag(String taskId, boolean isImportant, int position);
    void addTask(String taskListId, String title);
}
