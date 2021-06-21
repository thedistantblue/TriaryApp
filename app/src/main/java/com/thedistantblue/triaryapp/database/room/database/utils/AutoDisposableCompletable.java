package com.thedistantblue.triaryapp.database.room.database.utils;

import io.reactivex.rxjava3.functions.Action;

public interface AutoDisposableCompletable {
    void subscribe(Action onComplete);
}