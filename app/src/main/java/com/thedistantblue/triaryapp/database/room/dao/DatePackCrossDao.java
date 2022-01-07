package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.base.DatePackCrossRef;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface DatePackCrossDao extends BaseDao<DatePackCrossRef> {
    @Transaction
    @Query("SELECT * from dates_table where uuid = :datesId")
    Single<DatePackCrossRef> findById(String datesId);

    @Transaction
    @Query("SELECT * from dates_table")
    Single<List<DatePackCrossRef>> findAll();
}