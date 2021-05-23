package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thedistantblue.triaryapp.entities.Running;

import java.util.List;

@Dao
public interface RunningDao {
    @Insert
    void create(Running running);

    @Update
    void save(Running running);

    @Delete
    void delete(Running running);

    @Transaction
    @Query("SELECT * from running_table where runningUUID = :runningId")
    Running findById(String runningId);

    @Transaction
    @Query("SELECT * FROM running_table")
    List<Running> findAll();
}