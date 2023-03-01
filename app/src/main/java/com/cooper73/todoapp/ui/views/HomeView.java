package com.cooper73.todoapp.ui.views;

import com.cooper73.todoapp.ui.viewmodels.TaskListViewModel;

public interface HomeView extends BaseView {
    void hideActionBar();
    void showProfile();
    void showImportantTasks();
    void showAllTasks();
    void showTaskList(TaskListViewModel viewModel);
    void showCreateListDialog();
}
