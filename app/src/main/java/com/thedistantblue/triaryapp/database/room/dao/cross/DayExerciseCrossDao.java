package com.thedistantblue.triaryapp.database.room.dao.cross;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.cross.DayExerciseCrossRef;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DayExerciseCrossDao extends BaseDao<DayExerciseCrossRef> {
    @Transaction
    @Query("SELECT * from days_with_exercises_table where dayId = :datesId")
    Single<DayExerciseCrossRef> findById(String datesId);

    @Transaction
    @Query("SELECT * from days_with_exercises_table where dayId in (:ids)")
    Single<List<DayExerciseCrossRef>> findAllById(Collection<String> ids);

    @Transaction
    @Query("SELECT * from days_with_exercises_table")
    Single<List<DayExerciseCrossRef>> findAll();
}