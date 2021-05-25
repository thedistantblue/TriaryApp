package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.entities.composite.ExerciseWithExerciseSet;

import java.util.List;

@Dao
public interface ExerciseWithExerciseSetDao {
    @Transaction
    @Query("SELECT * from exercise_table where exerciseUUID = :exerciseId")
    ExerciseWithExerciseSet findById(String exerciseId);

    @Transaction
    @Query("SELECT * from exercise_table")
    List<ExerciseWithExerciseSet> findAll();
}