package com.thedistantblue.triaryapp.database.room.database;

import java.util.Collection;
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
    public void create(Completable completable, Action onCompleteAction) {
        completableAction(completable, onCompleteAction);
    }

    @Override
    public void save(Completable completable, Action onCompleteAction) {
        completableAction(completable, onCompleteAction);
    }

    @Override
    public void delete(Completable completable, Action onCompleteAction) {
        completableAction(completable, onCompleteAction);
    }

    @Override
    public <T> void findById(Single<T> single, Consumer<T> onSuccessConsumer) {
        singleAction(single, onSuccessConsumer, null);
    }

    @Override
    public <T> void findById(Single<T> single, Consumer<T> onSuccessConsumer, Consumer<Throwable> onErrorConsumer) {
        singleAction(single, onSuccessConsumer, onErrorConsumer);
    }

    @Override
    public <T extends Collection<T>> void findAll(Single<T> single, Consumer<T> onSuccessConsumer) {
        singleAction(single, onSuccessConsumer, null);
    }

    @Override
    public <T extends Collection<T>> void findAll(Single<T> single, Consumer<T> onSuccessConsumer, Consumer<Throwable> onErrorConsumer) {
        singleAction(single, onSuccessConsumer, onErrorConsumer);
    }
}