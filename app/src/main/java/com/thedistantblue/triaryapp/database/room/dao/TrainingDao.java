package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.thedistantblue.triaryapp.entities.Training;

import java.util.List;

@Dao
public interface TrainingDao {
    @Insert
    void create(Training training);

    @Delete
    void delete(Training training);

    @Query("SELECT * FROM training_table")
    List<Training> findAll();
}