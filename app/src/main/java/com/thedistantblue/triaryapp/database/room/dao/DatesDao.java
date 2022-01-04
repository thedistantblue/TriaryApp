package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.base.Dates;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DatesDao extends BaseDao<Dates> {
    @Insert
    Completable create(Dates dates);

    @Update
    Completable save(Dates dates);

    @Delete
    Completable delete(Dates dates);

    @Query("SELECT * from dates_table where uuid = :datesId")
    Single<Dates> findById(String datesId);

    @Query("SELECT * FROM dates_table")
    Single<List<Dates>> findAll();
}