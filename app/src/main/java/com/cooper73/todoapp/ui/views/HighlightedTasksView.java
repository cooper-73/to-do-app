package com.cooper73.todoapp.ui.views;

import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;

import java.util.ArrayList;

public interface HighlightedTasksView extends BaseView {
    void setActionBar();
    void showTasks(ArrayList<TaskViewModel> tasks);
    void notifyTaskRemovedAndRangeChanged(int position, int itemCount);
    void notifyTaskUpdated(int position);
}
