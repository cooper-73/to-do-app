package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.daos.TaskListDao;
import com.cooper73.todoapp.data.entities.TaskList;

public class TaskListInteractorImpl implements TaskListInteractor {
    private final TaskListInteractor.Callbacks callbacks;

    public TaskListInteractorImpl(TaskListInteractor.Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void renameTaskList(String taskListId, String newTitle) {
        TaskListDao taskListDao = MyApplication.getDatabase().taskListDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                TaskList taskList = taskListDao.getById(taskListId);
                taskList.setTitle(newTitle);
                taskListDao.updateTaskList(taskList);
                callbacks.successRenameTaskList(newTitle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void deleteTaskList(String taskListId) {
        TaskListDao taskListDao = MyApplication.getDatabase().taskListDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                TaskList taskList = taskListDao.getById(taskListId);
                taskListDao.deleteTaskList(taskList);
                callbacks.successDeleteTaskList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void getToDoTasks(String taskListId) {

    }

    @Override
    public void getCompletedTasks(String taskListId) {

    }
}
