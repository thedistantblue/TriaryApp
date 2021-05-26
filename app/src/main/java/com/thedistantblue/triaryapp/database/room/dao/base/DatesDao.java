package com.thedistantblue.triaryapp.database.room.dao.base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thedistantblue.triaryapp.entities.base.Dates;
import com.thedistantblue.triaryapp.entities.base.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DatesDao {
    @Insert
    Completable create(Dates dates);

    @Update
    Completable save(Dates dates);

    @Delete
    Completable delete(Dates dates);

    @Query("SELECT * from dates_table where datesUUID = :datesId")
    Single<Dates> findById(String datesId);

    @Query("SELECT * FROM dates_table")
    Single<List<Dates>> findAll();
}