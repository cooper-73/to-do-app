package com.cooper73.todoapp.ui.viewmodels;

public class TaskListViewModel {
    private String id;
    private String title;

    public TaskListViewModel() {

    }

    public TaskListViewModel(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
