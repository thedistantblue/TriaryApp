package com.thedistantblue.triaryapp.database.room.dao.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.entities.base.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void create(Exercise exercise);

    @Update
    void save(Exercise dates);

    @Delete
    void delete(Exercise exercise);

    @Transaction
    @Query("SELECT * from exercise_table where exerciseUUID = :exerciseId")
    Exercise findById(String exerciseId);

    @Transaction
    @Query("SELECT * FROM exercise_table")
    List<Exercise> findAll();
}