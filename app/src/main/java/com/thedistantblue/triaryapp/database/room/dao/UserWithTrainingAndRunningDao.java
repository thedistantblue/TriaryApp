package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.database.room.dao.base.ReadOnlyDao;
import com.thedistantblue.triaryapp.entities.composite.UserWithTrainingAndRunning;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserWithTrainingAndRunningDao extends ReadOnlyDao<UserWithTrainingAndRunning> {
    @Transaction
    @Query("SELECT * from user_table where userId = :userId")
    Single<UserWithTrainingAndRunning> findById(String userId);

    @Transaction
    @Query("SELECT * from user_table where userId in (:ids)")
    Single<List<UserWithTrainingAndRunning>> findAllById(Collection<String> ids);

    @Transaction
    @Query("SELECT * from user_table")
    Single<List<UserWithTrainingAndRunning>> findAll();
}