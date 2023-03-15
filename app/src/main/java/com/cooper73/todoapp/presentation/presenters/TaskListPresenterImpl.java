package com.cooper73.todoapp.presentation.presenters;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.entities.Task;
import com.cooper73.todoapp.data.interactors.TaskListInteractor;
import com.cooper73.todoapp.data.interactors.TaskListInteractorImpl;
import com.cooper73.todoapp.ui.mappers.TaskMapper;
import com.cooper73.todoapp.ui.viewmodels.TaskViewModel;
import com.cooper73.todoapp.ui.views.TaskListView;

import java.util.ArrayList;
import java.util.List;

public class TaskListPresenterImpl implements TaskListPresenter, TaskListInteractor.Callbacks {
    private final TaskListView view;
    private final TaskListInteractor interactor;
    private ArrayList<TaskViewModel> tasks;
    private final String taskListId;

    public TaskListPresenterImpl(TaskListView view, String taskListId) {
        this.view = view;
        this.interactor = new TaskListInteractorImpl(this);
        this.taskListId = taskListId;
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
        interactor.getToDoTasks(taskListId);
    }

    @Override
    public void loadCompletedTasks(String taskListId) {
        interactor.getCompletedTasks(taskListId);
    }

    @Override
    public void updateTaskCompletedFlag(String taskId, boolean isCompleted, int position) {
        interactor.updateTaskCompletedFlag(taskId, isCompleted, position);
    }

    @Override
    public void addTask(String taskListId, String title) {
        interactor.addTask(taskListId, title);
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
        MyApplication.getMainThreadHandler().post(() -> {
            tasks = TaskMapper.from(toDoTasks);
            view.showToDoTasks(tasks);
        });
    }

    @Override
    public void successCompletedTasks(List<Task> completedTasks) {
        MyApplication.getMainThreadHandler().post(() -> {
            tasks = TaskMapper.from(completedTasks);
            view.showToDoTasks(tasks);
        });
    }

    @Override
    public void successUpdateTaskCompletedFlag(String taskId, int position) {
        tasks.remove(position);
        MyApplication.getMainThreadHandler().post(() -> view.notifyTaskRemovedAndRangeChanged(position, tasks.size() - position));
    }

    @Override
    public void successAddTask(Task newTask) {
        MyApplication.getMainThreadHandler().post(() -> {
            tasks.add(TaskMapper.from(newTask));
            view.notifyNewTaskInserted(tasks.size() - 1);
        });
    }
}
