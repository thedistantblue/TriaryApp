package com.thedistantblue.triaryapp.database.room.dao.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.entities.base.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDao {
    @Insert
    Completable create(User user);

    @Update
    Completable save(User user);

    @Delete
    Completable delete(User user);

    @Query("SELECT * from user_table where user_id = :userId")
    Single<User> findById(String userId);

    @Query("SELECT * FROM user_table")
    Single<List<User>> findAll();
}