package com.thedistantblue.triaryapp.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;

import io.reactivex.rxjava3.disposables.Disposable;

public interface AutoDisposableLifecycleObserver extends LifecycleObserver {
    void bindTo(Lifecycle lifecycle);
    void add(Disposable disposable);
}