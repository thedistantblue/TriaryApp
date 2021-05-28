package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.base.Running;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface RunningDao extends BaseDao<Running> {
    @Insert
    Completable create(Running running);

    @Update
    Completable save(Running running);

    @Delete
    Completable delete(Running running);

    @Query("SELECT * from running_table where runningUUID = :runningId")
    Single<Running> findById(String runningId);

    @Query("SELECT * FROM running_table")
    Single<List<Running>> findAll();
}