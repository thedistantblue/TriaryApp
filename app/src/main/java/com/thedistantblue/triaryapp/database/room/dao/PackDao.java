package com.thedistantblue.triaryapp.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.entities.base.Exercise;
import com.thedistantblue.triaryapp.entities.base.Pack;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PackDao extends BaseDao<Pack> {
    @Insert
    Completable create(Pack pack);

    @Update
    Completable save(Pack pack);

    @Delete
    Completable delete(Pack pack);

    @Query("SELECT * from pack_table where packId = :packId")
    Single<Pack> findById(String packId);

    @Query("SELECT * from pack_table where packId in (:ids)")
    Single<List<Pack>> findAllById(Collection<String> ids);

    @Query("SELECT * FROM pack_table")
    Single<List<Pack>> findAll();
}