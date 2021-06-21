package com.thedistantblue.triaryapp.database.room.database.utils;

import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
public class AutoDisposableSingleImpl<T> implements AutoDisposableSingle<T> {

    private final Single<T> single;

    @Override
    public void subscribe(Consumer<? super T> onSuccess) {
        Disposable disposable = single.subscribe(onSuccess::accept);
        disposable.dispose();
    }
}