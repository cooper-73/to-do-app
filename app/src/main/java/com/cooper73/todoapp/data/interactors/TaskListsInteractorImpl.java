package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.daos.TaskListDao;
import com.cooper73.todoapp.data.entities.TaskList;

import java.util.List;

public class TaskListsInteractorImpl implements TaskListsInteractor {

    private final TaskListsInteractor.Callbacks callbacks;

    public TaskListsInteractorImpl(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void getAllTaskLists(String userId) {
        TaskListDao taskListDao = MyApplication.getDatabase().taskListDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                List<TaskList> result = taskListDao.getAllByUserId(userId);
                callbacks.successGetAllTaskLists(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void createTaskList(String userId, String title) {
        TaskListDao taskListDao = MyApplication.getDatabase().taskListDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                TaskList newTaskList = new TaskList(userId, title);
                taskListDao.insertNewTaskList(newTaskList);
                callbacks.successCreateTaskList(newTaskList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
