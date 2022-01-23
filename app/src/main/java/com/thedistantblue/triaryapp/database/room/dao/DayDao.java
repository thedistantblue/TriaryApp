package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.base.Day;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DayDao extends BaseDao<Day> {
    @Insert
    Completable create(Day dates);

    @Update
    Completable save(Day dates);

    @Delete
    Completable delete(Day dates);

    @Query("SELECT * from day_table where dayId = :datesId")
    Single<Day> findById(String datesId);

    @Query("SELECT * from day_table where dayId in (:ids)")
    Single<List<Day>> findAllById(Collection<String> ids);

    @Query("SELECT * FROM day_table")
    Single<List<Day>> findAll();
}