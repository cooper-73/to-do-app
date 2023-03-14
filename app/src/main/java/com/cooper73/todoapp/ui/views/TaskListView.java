package com.cooper73.todoapp.ui.views;

import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;

import java.util.ArrayList;

public interface TaskListView extends BaseView {
    void getExtras();
    void setActionBar();
    void setActionBarTitle(String title);
    void showRenameListDialog();
    void showDeleteListDialog();
    void onClickToDoTasks();
    void onClickCompletedTasks();
    void showToDoTasks(ArrayList<TaskViewModel> toDoTasks);
    void showCompletedTasks(ArrayList<TaskViewModel> completedTasks);
    void markTaskAsCompleted();
    void markTaskAsImportant();
    void showAddTaskActivity();
    void notifyNewTaskInserted(int position);
    void finishActivity();
}
