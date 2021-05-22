package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.thedistantblue.triaryapp.entities.ExerciseSet;

import java.util.List;

@Dao
public interface ExerciseSetDao {
    @Insert
    void create(ExerciseSet exerciseSet);

    @Delete
    void delete(ExerciseSet exerciseSet);

    @Query("SELECT * FROM exercise_set_table")
    List<ExerciseSet> findAll();
}