package com.cooper73.todoapp.presentation.presenters;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.entities.Task;
import com.cooper73.todoapp.data.interactors.AllTasksInteractor;
import com.cooper73.todoapp.data.interactors.AllTasksInteractorImpl;
import com.cooper73.todoapp.ui.mappers.TaskMapper;
import com.cooper73.todoapp.ui.views.HighlightedTasksView;

import java.util.List;

public class AllTasksPresenterImpl implements AllTasksPresenter, AllTasksInteractor.Callbacks {
    private final HighlightedTasksView view;
    private final AllTasksInteractor interactor;

    public AllTasksPresenterImpl(HighlightedTasksView view) {
        this.view = view;
        interactor = new AllTasksInteractorImpl(this);
    }

    @Override
    public void loadAllTasks(String userId) {
        interactor.getAllTasks(userId);
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
    public void successGetAllTasks(List<Task> allTasks) {
        MyApplication.getMainThreadHandler().post(() -> view.showTasks(TaskMapper.from(allTasks)));
    }

    @Override
    public void successUpdateTaskCompletedFlag(int position) {
        MyApplication.getMainThreadHandler().post(() -> view.notifyTaskUpdated(position));
    }

    @Override
    public void successUpdateTaskImportantFlag(int position) {
        MyApplication.getMainThreadHandler().post(() -> view.notifyTaskUpdated(position));
    }
}
