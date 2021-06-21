package com.thedistantblue.triaryapp.database.room.database.utils;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
public class AutoDisposableCompletableImpl implements AutoDisposableCompletable {

    private final Completable completable;

    @Override
    public void subscribe(Action onComplete) {
        Disposable disposable = completable.subscribe(onComplete);
        disposable.dispose();
    }
}