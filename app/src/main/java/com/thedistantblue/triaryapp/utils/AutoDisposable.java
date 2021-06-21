package com.thedistantblue.triaryapp.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class AutoDisposable implements LifecycleObserver {

    private CompositeDisposable compositeDisposable;

    public void bindTo(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
        compositeDisposable = new CompositeDisposable();
    }

    public void add(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        compositeDisposable.clear();
    }
}