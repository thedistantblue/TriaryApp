package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.base.Training;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface TrainingDao extends BaseDao<Training> {
    @Insert
    Completable create(Training training);

    @Update
    Completable save(Training training);

    @Delete
    Completable delete(Training training);

    @Query("SELECT * from training_table where trainingUUID = :trainingId")
    Single<Training> findById(String trainingId);

    @Query("SELECT * FROM training_table")
    Single<List<Training>> findAll();
}