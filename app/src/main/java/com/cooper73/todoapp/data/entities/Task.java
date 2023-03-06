package com.cooper73.todoapp.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(
        tableName = "tasks",
        foreignKeys = {
                @ForeignKey(entity = TaskList.class, parentColumns = "id", childColumns = "task_list_id")
        },
        indices = @Index(value="task_list_id")
)
public class Task {
    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "task_list_id")
    private String taskListId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "created_at")
    private Date createdAt;

    @ColumnInfo(name = "due_date")
    private Date dueDate;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "is_completed")
    private boolean isCompleted;

    @ColumnInfo(name = "is_important")
    private boolean isImportant;

    public Task(String taskListId, String title, Date dueDate, String description, boolean isCompleted, boolean isImportant) {
        this.id = UUID.randomUUID().toString();
        this.taskListId = taskListId;
        this.title = title;
        this.createdAt = new Date();
        this.dueDate = dueDate;
        this.description = description;
        this.isCompleted = isCompleted;
        this.isImportant = isImportant;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(String taskListId) {
        this.taskListId = taskListId;
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
