package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.entities.composite.UserWithTrainingAndRunning;

import java.util.List;

@Dao
public interface UserWithTrainingAndRunningDao {
    @Transaction
    @Query("SELECT * from user_table where userID = :userId")
    UserWithTrainingAndRunning findById(String userId);

    @Transaction
    @Query("SELECT * from user_table")
    List<UserWithTrainingAndRunning> findAll();
}