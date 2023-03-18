package com.cooper73.todoapp.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cooper73.todoapp.data.entities.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email")
    User getUserByEmail(String email);

    @Insert
    void insertUser(User user);
}
