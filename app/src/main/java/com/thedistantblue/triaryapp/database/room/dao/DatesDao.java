package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.entities.Dates;

import java.util.List;

@Dao
public interface DatesDao {
    @Insert
    void create(Dates dates);

    @Update
    void save(Dates dates);

    @Delete
    void delete(Dates dates);

    @Transaction
    @Query("SELECT * from dates_table where datesUUID = :datesId")
    Dates findById(String datesId);

    @Transaction
    @Query("SELECT * FROM dates_table")
    List<Dates> findAll();
}