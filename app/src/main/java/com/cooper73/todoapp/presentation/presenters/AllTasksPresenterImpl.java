package com.cooper73.todoapp.presentation.presenters;

import com.cooper73.todoapp.data.entities.Task;
import com.cooper73.todoapp.data.interactors.AllTasksInteractor;
import com.cooper73.todoapp.data.interactors.AllTasksInteractorImpl;
import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;
import com.cooper73.todoapp.ui.views.HighlightedTasksView;

import java.util.ArrayList;
import java.util.List;

public class AllTasksPresenterImpl implements AllTasksPresenter, AllTasksInteractor.Callbacks {
    private final HighlightedTasksView view;
    private final AllTasksInteractor interactor;
    private ArrayList<TaskViewModel> tasks;

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
        interactor.updateTaskCompletedFlag(taskId, isImportant, position);
    }

    @Override
    public void successGetAllTasks(List<Task> allTasks) {

    }

    @Override
    public void successUpdateTaskCompletedFlag(int position) {

    }

    @Override
    public void successUpdateTaskImportantFlag(int position) {

    }
}
