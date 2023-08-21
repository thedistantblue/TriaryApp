package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.database.room.dao.base.ReadOnlyDao;
import com.thedistantblue.triaryapp.entities.composite.ExerciseWithExerciseSet;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ExerciseWithExerciseSetDao extends ReadOnlyDao<ExerciseWithExerciseSet> {
    @Transaction
    @Query("SELECT * from exercise_table where exerciseId = :exerciseId")
    Single<ExerciseWithExerciseSet> findById(String exerciseId);

    @Transaction
    @Query("SELECT * from exercise_table where exerciseId in (:ids)")
    Single<List<ExerciseWithExerciseSet>> findAllById(Collection<String> ids);

    @Transaction
    @Query("SELECT * from exercise_table")
    Single<List<ExerciseWithExerciseSet>> findAll();
}