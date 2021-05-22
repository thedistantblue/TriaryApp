package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.thedistantblue.triaryapp.entities.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void create(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user_table")
    List<User> findAll();
}