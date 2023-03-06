package com.cooper73.todoapp.presentation.presenters;

public interface HomePresenter {
    void loadImportantTasks();
    void loadAllTasks();
    void loadAllTaskLists();
    void createTaskList(String title);
}
