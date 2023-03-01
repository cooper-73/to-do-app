package com.cooper73.todoapp.ui.viewmodels;

public class TaskListViewModel {
    private final int id;
    private String title;

    public TaskListViewModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
