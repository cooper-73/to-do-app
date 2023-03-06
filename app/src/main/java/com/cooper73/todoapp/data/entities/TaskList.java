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
        tableName = "task_lists",
        foreignKeys = {
            @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id")
        },
        indices = @Index(value="user_id")
)
public class TaskList {
    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "user_id")
    private String userId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "created_at")
    private Date createdAt;

    public TaskList(String userId, String title) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.title = title;
        this.createdAt = new Date();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
