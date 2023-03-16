package com.cooper73.todoapp.presentation.presenters;

public interface ImportantTasksPresenter {
    void loadImportantTask(String userId);
    void updateTaskCompletedFlag(String taskId, boolean isCompleted, int position);
    void updateTaskImportantFlag(String taskId, boolean isImportant, int position);
}
