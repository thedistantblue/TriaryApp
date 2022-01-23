package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.database.room.dao.base.ReadOnlyDao;
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface ExerciseDetailsDao extends ReadOnlyDao<ExerciseDetails> {
    @Transaction
    @Query("SELECT * from exercise_table where exerciseId = :exerciseId")
    Single<ExerciseDetails> findById(String exerciseId);

    @Transaction
    @Query("SELECT * from exercise_table where exerciseId in (:ids)")
    Single<List<ExerciseDetails>> findAllById(Collection<String> ids);

    @Transaction
    @Query("SELECT * from exercise_table")
    Single<List<ExerciseDetails>> findAll();
}