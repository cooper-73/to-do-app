package com.cooper73.todoapp.presentation.presenters;

public interface HomePresenter {
    void loadAllTaskLists(String userId);
    void createTaskList(String userId, String title);
}
