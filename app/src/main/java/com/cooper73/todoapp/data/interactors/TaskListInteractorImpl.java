package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.daos.TaskDao;
import com.cooper73.todoapp.data.daos.TaskListDao;
import com.cooper73.todoapp.data.entities.Task;
import com.cooper73.todoapp.data.entities.TaskList;

import java.util.List;

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
        TaskDao taskDao = MyApplication.getDatabase().taskDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                List<Task> toDoTasks = taskDao.getAllToDoByTaskListId(taskListId);
                callbacks.successGetToDoTasks(toDoTasks);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void getCompletedTasks(String taskListId) {
        TaskDao taskDao = MyApplication.getDatabase().taskDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                List<Task> completedTasks = taskDao.getAllCompletedByTaskListId(taskListId);
                callbacks.successCompletedTasks(completedTasks);
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
                callbacks.successUpdateTaskCompletedFlag(taskId, position);
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

    @Override
    public void addTask(String taskListId, String title) {
        TaskDao taskDao = MyApplication.getDatabase().taskDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                Task newTask = new Task(taskListId, title);
                taskDao.insertNewTask(newTask);
                callbacks.successAddTask(newTask);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
