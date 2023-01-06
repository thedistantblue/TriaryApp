package com.thedistantblue.triaryapp.database.room.dao.details;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.database.room.dao.base.ReadOnlyDao;
import com.thedistantblue.triaryapp.entities.composite.details.TrainingDetails;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface TrainingDetailsDao extends ReadOnlyDao<TrainingDetails> {
    @Transaction
    @Query("SELECT * from training_table where trainingId = :trainingId")
    Single<TrainingDetails> findById(String trainingId);

    @Transaction
    @Query("SELECT * from training_table where trainingId in (:ids)")
    Single<List<TrainingDetails>> findAllById(Collection<String> ids);

    @Transaction
    @Query("SELECT * from training_table")
    Single<List<TrainingDetails>> findAll();
}