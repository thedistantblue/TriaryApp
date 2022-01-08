package com.thedistantblue.triaryapp.database.room.dao.base;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ReadOnlyDao<T> {
    Single<T> findById(String id);
    Single<List<T>> findAllById(Collection<String> ids);
    Single<List<T>> findAll();
}