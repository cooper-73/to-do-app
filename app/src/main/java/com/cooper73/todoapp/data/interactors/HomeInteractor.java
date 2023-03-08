package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.data.entities.TaskList;

import java.util.List;

public interface HomeInteractor {
    void getAllTaskLists(String userId);
    void createTaskList(String userId, String title);

    interface Callbacks {
        void successGetAllTaskLists(List<TaskList> taskLists);
        void successCreateTaskList(TaskList taskList);
    }
}
