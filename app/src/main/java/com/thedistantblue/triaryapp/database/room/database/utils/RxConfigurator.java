package com.thedistantblue.triaryapp.database.room.database.utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RxConfigurator {
    public static Completable configureThreading(Completable completable) {
        return completable.subscribeOn(Schedulers.io())
                          .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Single<T> configureThreading(Single<T> single) {
        return single.subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread());
    }
}