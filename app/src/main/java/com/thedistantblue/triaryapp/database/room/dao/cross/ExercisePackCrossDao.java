package com.thedistantblue.triaryapp.database.room.dao.cross;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.cross.ExercisePackCrossRef;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface ExercisePackCrossDao extends BaseDao<ExercisePackCrossRef> {
    @Transaction
    @Query("SELECT * from exercises_with_packs_table where exerciseId = :exerciseId")
    Single<ExercisePackCrossRef> findById(String exerciseId);

    @Transaction
    @Query("SELECT * from exercises_with_packs_table where exerciseId in (:ids)")
    Single<List<ExercisePackCrossRef>> findAllById(Collection<String> ids);

    @Transaction
    @Query("SELECT * from exercises_with_packs_table")
    Single<List<ExercisePackCrossRef>> findAll();
}