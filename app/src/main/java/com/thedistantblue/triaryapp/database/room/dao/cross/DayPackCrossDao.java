package com.thedistantblue.triaryapp.database.room.dao.cross;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.cross.DayPackCrossRef;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DayPackCrossDao extends BaseDao<DayPackCrossRef> {
    @Transaction
    @Query("SELECT * from days_with_packs_table where dayId = :datesId")
    Single<DayPackCrossRef> findById(String datesId);

    @Query("SELECT * from days_with_packs_table where dayId in (:ids)")
    Single<List<DayPackCrossRef>> findAllById(Collection<String> ids);

    @Transaction
    @Query("SELECT * from days_with_packs_table")
    Single<List<DayPackCrossRef>> findAll();
}