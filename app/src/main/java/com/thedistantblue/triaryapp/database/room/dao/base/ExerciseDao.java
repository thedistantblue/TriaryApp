package com.thedistantblue.triaryapp.database.room.dao.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.entities.base.Exercise;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ExerciseDao {
    @Insert
    Completable create(Exercise exercise);

    @Update
    Completable save(Exercise exercise);

    @Delete
    Completable delete(Exercise exercise);

    @Query("SELECT * from exercise_table where exerciseUUID = :exerciseId")
    Single<Exercise> findById(String exerciseId);

    @Query("SELECT * FROM exercise_table")
    Single<List<Exercise>> findAll();
}