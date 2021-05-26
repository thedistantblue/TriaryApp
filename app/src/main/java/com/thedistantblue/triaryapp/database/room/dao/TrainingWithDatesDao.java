package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.entities.composite.TrainingWithDates;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface TrainingWithDatesDao {
    @Transaction
    @Query("SELECT * from training_table where trainingUUID = :trainingId")
    Single<TrainingWithDates> findById(String trainingId);

    @Transaction
    @Query("SELECT * from training_table")
    Single<List<TrainingWithDates>> findAll();
}