package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.daos.TaskDao;
import com.cooper73.todoapp.data.entities.Task;

import java.util.List;

public class AllTasksInteractorImpl implements AllTasksInteractor {
    private final AllTasksInteractor.Callbacks callbacks;

    public AllTasksInteractorImpl(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void getAllTasks(String userId) {
        TaskDao taskDao = MyApplication.getDatabase().taskDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                List<Task> allTasks = taskDao.getAllTasksByUserId(userId);
                callbacks.successGetAllTasks(allTasks);
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
                callbacks.successUpdateTaskCompletedFlag(position);
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
