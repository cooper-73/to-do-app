package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.data.entities.Task;

import java.util.List;

public interface TaskListInteractor {
    void renameTaskList(String taskListId, String newTitle);
    void deleteTaskList(String taskListId);
    void getToDoTasks(String taskListId);
    void getCompletedTasks(String taskListId);

    interface Callbacks {
        void successRenameTaskList(String newTitle);
        void successDeleteTaskList();
        void successGetToDoTasks(List<Task> toDoTasks);
        void successCompletedTasks(List<Task> completedTasks);
    }
}
