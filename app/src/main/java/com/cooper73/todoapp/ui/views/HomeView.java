package com.cooper73.todoapp.ui.views;

import com.cooper73.todoapp.ui.viewmodels.TaskListViewModel;

import java.util.ArrayList;

public interface HomeView extends BaseView {
    void getExtras();
    void hideActionBar();
    void showProfile();
    void showImportantTasks();
    void showAllTasks();
    void showTaskLists(ArrayList<TaskListViewModel> taskLists);
    void showTaskList(TaskListViewModel viewModel);
    void showCreateListDialog();
    void notifyNewTaskListInserted(int position);
}
