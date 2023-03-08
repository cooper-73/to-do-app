package com.cooper73.todoapp.presentation.presenters;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.entities.Task;
import com.cooper73.todoapp.data.interactors.TaskListInteractor;
import com.cooper73.todoapp.data.interactors.TaskListInteractorImpl;
import com.cooper73.todoapp.ui.views.TaskListView;

import java.util.List;

public class TaskListPresenterImpl implements TaskListPresenter, TaskListInteractor.Callbacks {
    private final TaskListView view;
    private final TaskListInteractor interactor;

    public TaskListPresenterImpl(TaskListView view) {
        this.view = view;
        this.interactor = new TaskListInteractorImpl(this);
    }

    @Override
    public void renameTaskList(String taskListId, String newTitle) {
        interactor.renameTaskList(taskListId, newTitle);
    }

    @Override
    public void deleteTaskList(String taskListId) {
        interactor.deleteTaskList(taskListId);
    }

    @Override
    public void loadToDoTasks(String taskListId) {

    }

    @Override
    public void loadCompletedTasks(String taskListId) {

    }

    //    Callbacks

    @Override
    public void successRenameTaskList(String newTitle) {
        MyApplication.getMainThreadHandler().post(() -> view.setActionBarTitle(newTitle));
    }

    @Override
    public void successDeleteTaskList() {
        MyApplication.getMainThreadHandler().post(() -> view.finishActivity());
    }

    @Override
    public void successGetToDoTasks(List<Task> toDoTasks) {

    }

    @Override
    public void successCompletedTasks(List<Task> completedTasks) {

    }
}
