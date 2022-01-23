package com.thedistantblue.triaryapp.database.room.dao.base;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import io.reactivex.rxjava3.core.Completable;

public interface BaseDao<T> extends ReadOnlyDao<T> {
    @Insert
    Completable create(T entity);
    @Update
    Completable save(T entity);
    @Delete
    Completable delete(T entity);
}