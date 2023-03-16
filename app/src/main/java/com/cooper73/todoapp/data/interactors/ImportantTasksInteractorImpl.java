package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.daos.TaskDao;
import com.cooper73.todoapp.data.entities.Task;

import java.util.List;

public class ImportantTasksInteractorImpl implements ImportantTasksInteractor {
    private final ImportantTasksInteractor.Callbacks callbacks;

    public ImportantTasksInteractorImpl(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void getImportantTasks(String userId) {
        TaskDao taskDao = MyApplication.getDatabase().taskDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                List<Task> importantTasks = taskDao.getAllImportantTasksByUserId(userId);
                callbacks.successGetImportantTasks(importantTasks);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void updateTaskCompletedFlag(String taskId, boolean isCompleted, int position) {
        TaskDao taskDao = MyApplication.getDatabase().taskDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                Task task = taskDao.getById(taskId);
                task.setCompleted(isCompleted);
                taskDao.updateTask(task);
                callbacks.successUpdateTaskCompletedFlag(isCompleted, position);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void updateTaskImportantFlag(String taskId, boolean isImportant, int position) {
        TaskDao taskDao = MyApplication.getDatabase().taskDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                Task task = taskDao.getById(taskId);
                task.setImportant(isImportant);
                taskDao.updateTask(task);
                callbacks.successUpdateTaskImportantFlag(position);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
