package com.thedistantblue.triaryapp.database.room.database;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;
import com.thedistantblue.triaryapp.database.room.dao.base.ReadOnlyDao;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DatabaseCallerImpl implements DatabaseCaller {

    private static void completableAction(Completable completable, Action onCompleteAction) {
        completable.subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(onCompleteAction);
    }

    private static <T> void singleAction(Single<T> single, Consumer<T> onSuccessConsumer, Consumer<Throwable> onErrorConsumer) {
        single.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeWith(createSingleObserver(onSuccessConsumer, onErrorConsumer));
    }

    private static <T> SingleObserver<T> createSingleObserver(Consumer<T> onSuccessConsumer, Consumer<Throwable> onErrorConsumer) {
        return new SingleObserver<T>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) { }

            @Override
            public void onSuccess(@NonNull T t) {
                onSuccessConsumer.accept(t);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (onErrorConsumer != null) {
                    onErrorConsumer.accept(e);
                }
            }
        };
    }

    @Override
    public <T> void create(BaseDao<T> dao, T entity, Action onCompleteAction) {
        completableAction(dao.create(entity), onCompleteAction);
    }

    @Override
    public <T> void save(BaseDao<T> dao, T entity, Action onCompleteAction) {
        completableAction(dao.save(entity), onCompleteAction);
    }

    @Override
    public <T> void delete(BaseDao<T> dao, T entity, Action onCompleteAction) {
        completableAction(dao.delete(entity), onCompleteAction);
    }

    @Override
    public <T> void findById(ReadOnlyDao<T> dao, String id, Consumer<T> onSuccessConsumer) {
        singleAction(dao.findById(id), onSuccessConsumer, null);
    }

    @Override
    public <T> void findById(ReadOnlyDao<T> dao, String id, Consumer<T> onSuccessConsumer, Consumer<Throwable> onErrorConsumer) {
        singleAction(dao.findById(id), onSuccessConsumer, onErrorConsumer);
    }

    @Override
    public <T> void findAll(ReadOnlyDao<T> dao, Consumer<List<T>> onSuccessConsumer) {
        singleAction(dao.findAll(), onSuccessConsumer, null);
    }

    @Override
    public <T> void findAll(ReadOnlyDao<T> dao, Consumer<List<T>> onSuccessConsumer, Consumer<Throwable> onErrorConsumer) {
        singleAction(dao.findAll(), onSuccessConsumer, onErrorConsumer);
    }
}