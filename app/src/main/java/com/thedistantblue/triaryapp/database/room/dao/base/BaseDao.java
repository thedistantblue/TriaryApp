package com.thedistantblue.triaryapp.database.room.dao.base;

import io.reactivex.rxjava3.core.Completable;

public interface BaseDao<T> extends ReadOnlyDao<T> {
    Completable create(T entity);
    Completable save(T entity);
    Completable delete(T entity);
}