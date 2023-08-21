package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.base.Exercise;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ExerciseDao extends BaseDao<Exercise> {
    @Insert
    Completable create(Exercise exercise);

    @Update
    Completable save(Exercise exercise);

    @Delete
    Completable delete(Exercise exercise);

    @Query("SELECT * from exercise_table where exerciseId = :exerciseId")
    Single<Exercise> findById(String exerciseId);

    @Query("SELECT * from exercise_table where exerciseId in (:ids)")
    Single<List<Exercise>> findAllById(Collection<String> ids);

    @Query("SELECT * FROM exercise_table")
    Single<List<Exercise>> findAll();
}