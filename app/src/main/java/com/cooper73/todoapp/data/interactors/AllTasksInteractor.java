package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.data.entities.Task;

import java.util.List;

public interface AllTasksInteractor {
    void getAllTasks(String userId);
    void updateTaskCompletedFlag(String taskId, boolean isCompleted, int position);
    void updateTaskImportantFlag(String taskId, boolean isImportant, int position);

    interface Callbacks {
        void successGetAllTasks(List<Task> allTasks);
        void successUpdateTaskCompletedFlag(int position);
        void successUpdateTaskImportantFlag(int position);
    }
}
