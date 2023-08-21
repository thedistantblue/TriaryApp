package com.thedistantblue.triaryapp.database.room.dao.details;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.thedistantblue.triaryapp.database.room.dao.base.ReadOnlyDao;
import com.thedistantblue.triaryapp.entities.composite.details.PackDetails;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface PackDetailsDao extends ReadOnlyDao<PackDetails> {
    @Transaction
    @Query("SELECT * from pack_table where packId = :packId")
    Single<PackDetails> findById(String packId);

    @Transaction
    @Query("SELECT * from pack_table where packId in (:ids)")
    Single<List<PackDetails>> findAllById(Collection<String> ids);

    @Transaction
    @Query("SELECT * from pack_table where trainingId = :trainingId")
    Single<List<PackDetails>> findAllByTrainingId(String trainingId);

    @Transaction
    @Query("SELECT * from pack_table")
    Single<List<PackDetails>> findAll();
}