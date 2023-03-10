package com.cooper73.todoapp.ui.views;

public interface TaskListView extends BaseView {
    void getExtras();
    void setActionBar();
    void setActionBarTitle(String title);
    void showRenameListDialog();
    void showDeleteListDialog();
    void onClickToDoTasks();
    void onClickCompletedTasks();
    void showToDoTasks();
    void showCompletedTasks();
    void markTaskAsCompleted();
    void markTaskAsImportant();
    void showAddTaskActivity();
    void finishActivity();
}
