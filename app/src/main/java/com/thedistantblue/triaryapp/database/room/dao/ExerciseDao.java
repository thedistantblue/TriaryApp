package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.thedistantblue.triaryapp.entities.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void create(Exercise exercise);

    @Delete
    void delete(Exercise exercise);

    @Query("SELECT * FROM exercise_table")
    List<Exercise> findAll();
}