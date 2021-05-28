package com.thedistantblue.triaryapp.database.room.database;

import java.util.Collection;
import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;

public interface DatabaseCaller {
    void create(Completable completable, Action onCompleteAction);
    void save(Completable completable, Action onCompleteAction);
    void delete(Completable completable, Action onCompleteAction);
    <T> void findById(Single<T> single, Consumer<T> onSuccessConsumer);
    <T> void findById(Single<T> single, Consumer<T> onSuccessConsumer, Consumer<Throwable> onErrorConsumer);
    <T extends Collection<T>> void findAll(Single<T> single, Consumer<T> onSuccessConsumer);
    <T extends Collection<T>> void findAll(Single<T> single, Consumer<T> onSuccessConsumer, Consumer<Throwable> onErrorConsumer);
}