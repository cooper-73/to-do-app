package com.cooper73.todoapp.presentation.presenters;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.entities.Task;
import com.cooper73.todoapp.data.interactors.ImportantTasksInteractor;
import com.cooper73.todoapp.data.interactors.ImportantTasksInteractorImpl;
import com.cooper73.todoapp.ui.mappers.TaskMapper;
import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;
import com.cooper73.todoapp.ui.views.HighlightedTasksView;

import java.util.ArrayList;
import java.util.List;

public class ImportantTasksPresenterImpl implements ImportantTasksPresenter, ImportantTasksInteractor.Callbacks {
    private final HighlightedTasksView view;
    private final ImportantTasksInteractor interactor;
    private ArrayList<TaskViewModel> importantTasks;

    public ImportantTasksPresenterImpl(HighlightedTasksView view) {
        this.view = view;
        this.interactor = new ImportantTasksInteractorImpl(this);
    }

    @Override
    public void loadImportantTask(String userId) {
        interactor.getImportantTasks(userId);
    }

    @Override
    public void updateTaskCompletedFlag(String taskId, boolean isCompleted, int position) {
        interactor.updateTaskCompletedFlag(taskId, isCompleted, position);
    }

    @Override
    public void updateTaskImportantFlag(String taskId, boolean isImportant, int position) {
        interactor.updateTaskImportantFlag(taskId, isImportant, position);
    }

    @Override
    public void successGetImportantTasks(List<Task> importantTasks) {
        MyApplication.getMainThreadHandler().post(() -> {
            this.importantTasks = TaskMapper.from(importantTasks);
            view.showTasks(this.importantTasks);
        });
    }

    @Override
    public void successUpdateTaskCompletedFlag(boolean isCompleted, int position) {
        MyApplication.getMainThreadHandler().post(() -> {
            importantTasks.get(position).setCompleted(isCompleted);
            view.notifyTaskUpdated(position);
        });
    }

    @Override
    public void successUpdateTaskImportantFlag(int position) {
        MyApplication.getMainThreadHandler().post(() -> {
            importantTasks.remove(position);
            view.notifyTaskRemovedAndRangeChanged(position, importantTasks.size() - position);
        });
    }
}
