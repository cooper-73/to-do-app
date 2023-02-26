package com.cooper73.todoapp.ui.views;

public interface HomeView extends BaseView {
    void hideActionBar();
    void showProfile();
    void showImportantTasks();
    void showAllTasks();
    void showTaskList();
    void showCreateListDialog();
}
