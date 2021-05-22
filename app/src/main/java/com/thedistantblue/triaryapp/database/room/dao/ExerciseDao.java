package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thedistantblue.triaryapp.entities.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void create(Exercise exercise);

    @Update
    void save(Exercise dates);

    @Delete
    void delete(Exercise exercise);

    @Query("SELECT * FROM exercise_table")
    List<Exercise> findAll();
}