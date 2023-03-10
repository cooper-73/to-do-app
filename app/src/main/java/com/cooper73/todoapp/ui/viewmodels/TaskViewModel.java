package com.cooper73.todoapp.ui.viewmodels;

import java.util.Date;

public class TaskViewModel {
    private String id;
    private String title;
    private Date createdAt;
    private Date dueDate;
    private String description;
    private boolean isCompleted;
    private boolean isImportant;

    public TaskViewModel() {
    }

    public TaskViewModel(String id, String title, Date createdAt, Date dueDate, String description, boolean isCompleted, boolean isImportant) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isImportant = isImportant;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }
}
