package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.database.room.dao.base.ReadOnlyDao;
import com.thedistantblue.triaryapp.entities.composite.TrainingWithDays;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface TrainingWithDaysDao extends ReadOnlyDao<TrainingWithDays> {
    @Transaction
    @Query("SELECT * from training_table where trainingId = :trainingId")
    Single<TrainingWithDays> findById(String trainingId);

    @Transaction
    @Query("SELECT * from training_table where trainingId in (:ids)")
    Single<List<TrainingWithDays>> findAllById(Collection<String> ids);

    @Transaction
    @Query("SELECT * from training_table")
    Single<List<TrainingWithDays>> findAll();
}