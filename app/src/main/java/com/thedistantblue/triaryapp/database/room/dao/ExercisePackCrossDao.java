package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.base.ExercisePackCrossRef;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface ExercisePackCrossDao extends BaseDao<ExercisePackCrossRef> {
    @Transaction
    @Query("SELECT * from exercise_table where uuid = :exerciseId")
    Single<ExercisePackCrossRef> findById(String exerciseId);

    @Transaction
    @Query("SELECT * from exercise_table")
    Single<List<ExercisePackCrossRef>> findAll();
}