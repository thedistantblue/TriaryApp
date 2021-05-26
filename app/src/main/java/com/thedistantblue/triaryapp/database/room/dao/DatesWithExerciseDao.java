package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.entities.composite.DatesWithExercise;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DatesWithExerciseDao {
    @Transaction
    @Query("SELECT * from dates_table where datesUUID = :datesId")
    Single<DatesWithExercise> findById(String datesId);

    @Transaction
    @Query("SELECT * from dates_table")
    Single<List<DatesWithExercise>> findAll();
}