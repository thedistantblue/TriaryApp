package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ExerciseSetDao extends BaseDao<ExerciseSet> {
    @Insert
    Completable create(ExerciseSet exerciseSet);

    @Update
    Completable save(ExerciseSet exerciseSet);

    @Delete
    Completable delete(ExerciseSet exerciseSet);

    @Query("SELECT * from exercise_set_table where exerciseSetUUID = :exerciseSetId")
    Single<ExerciseSet> findById(String exerciseSetId);

    @Query("SELECT * FROM exercise_set_table")
    Single<List<ExerciseSet>> findAll();
}