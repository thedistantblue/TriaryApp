package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.entities.composite.DatesWithExercise;

import java.util.List;

@Dao
public interface DatesWithExerciseDao {
    @Transaction
    @Query("SELECT * from dates_table where datesUUID = :datesId")
    DatesWithExercise findById(String datesId);

    @Transaction
    @Query("SELECT * from dates_table")
    List<DatesWithExercise> findAll();
}