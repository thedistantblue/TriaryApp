package com.thedistantblue.triaryapp.mainscreen;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.thedistantblue.triaryapp.utils.AutoDisposableLifecycleObserver;
import com.thedistantblue.triaryapp.utils.AutoDisposableLifecycleObserverImpl;

import io.reactivex.rxjava3.disposables.Disposable;

public class AutoDisposableFragment extends Fragment {
    protected static final AutoDisposableLifecycleObserver observer = new AutoDisposableLifecycleObserverImpl();

    protected static void withAutoDispose(Disposable disposable) {
        observer.add(disposable);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observer.bindTo(getLifecycle());
    }

    public void addDisposable(Disposable disposable) {
        observer.add(disposable);
    }
}