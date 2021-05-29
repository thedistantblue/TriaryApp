package com.thedistantblue.triaryapp.database.room.database;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.database.room.dao.base.ReadOnlyDao;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.rxjava3.functions.Action;

public interface DatabaseCaller {
    <T> void create(BaseDao<T> dao, T entity, Action onCompleteAction);
    <T> void save(BaseDao<T> dao, T entity, Action onCompleteAction);
    <T> void delete(BaseDao<T> dao, T entity, Action onCompleteAction);
    <T> void findById(ReadOnlyDao<T> dao, String id, Consumer<T> onSuccessConsumer);
    <T> void findById(ReadOnlyDao<T> dao, String id, Consumer<T> onSuccessConsumer, Consumer<Throwable> onErrorConsumer);
    <T> void findAll(ReadOnlyDao<T> dao, Consumer<List<T>> onSuccessConsumer);
    <T> void findAll(ReadOnlyDao<T> dao, Consumer<List<T>> onSuccessConsumer, Consumer<Throwable> onErrorConsumer);
}