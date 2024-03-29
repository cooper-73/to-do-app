package com.cooper73.todoapp.presentation.presenters;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.entities.TaskList;
import com.cooper73.todoapp.data.interactors.HomeInteractor;
import com.cooper73.todoapp.data.interactors.HomeInteractorImpl;
import com.cooper73.todoapp.ui.mappers.TaskListMapper;
import com.cooper73.todoapp.ui.viewmodels.TaskListViewModel;
import com.cooper73.todoapp.ui.views.HomeView;

import java.util.ArrayList;
import java.util.List;

public class HomePresenterImpl implements HomePresenter, HomeInteractor.Callbacks {
    private final HomeView view;
    private final HomeInteractor homeInteractor;
    private ArrayList<TaskListViewModel> taskLists;

    public HomePresenterImpl(HomeView view) {
        this.view = view;
        this.homeInteractor = new HomeInteractorImpl(this);
    }

    @Override
    public void loadAllTaskLists(String userId) {
        homeInteractor.getAllTaskLists(userId);
    }

    @Override
    public void createTaskList(String userId, String title) {
        homeInteractor.createTaskList(userId, title);
    }

    //    Callbacks

    @Override
    public void successGetAllTaskLists(List<TaskList> taskLists) {
        MyApplication.getMainThreadHandler().post(() -> {
            this.taskLists = TaskListMapper.from(taskLists);
            view.showTaskLists(this.taskLists);
        });
    }

    @Override
    public void successCreateTaskList(TaskList taskList) {
        MyApplication.getMainThreadHandler().post(() -> {
            this.taskLists.add(TaskListMapper.from(taskList));
            view.notifyNewTaskListInserted(this.taskLists.size() - 1);
        });
    }
}
