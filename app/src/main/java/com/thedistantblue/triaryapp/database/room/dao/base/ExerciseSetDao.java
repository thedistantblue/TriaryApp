package com.thedistantblue.triaryapp.database.room.dao.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.entities.base.ExerciseSet;

import java.util.List;

@Dao
public interface ExerciseSetDao {
    @Insert
    void create(ExerciseSet exerciseSet);

    @Update
    void save(ExerciseSet exerciseSet);

    @Delete
    void delete(ExerciseSet exerciseSet);

    @Transaction
    @Query("SELECT * from exercise_set_table where exerciseSetUUID = :exerciseSetId")
    ExerciseSet findById(String exerciseSetId);

    @Transaction
    @Query("SELECT * FROM exercise_set_table")
    List<ExerciseSet> findAll();
}