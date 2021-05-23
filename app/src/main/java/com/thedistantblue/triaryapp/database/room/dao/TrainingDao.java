package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.entities.Training;

import java.util.List;

@Dao
public interface TrainingDao {
    @Insert
    void create(Training training);

    @Update
    void save(Training training);

    @Delete
    void delete(Training training);

    @Transaction
    @Query("SELECT * from training_table where trainingUUID = :trainingId")
    Training findById(String trainingId);

    @Transaction
    @Query("SELECT * FROM training_table")
    List<Training> findAll();
}