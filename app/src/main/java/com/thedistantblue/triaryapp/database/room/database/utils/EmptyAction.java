package com.thedistantblue.triaryapp.database.room.database.utils;

import io.reactivex.rxjava3.functions.Action;

public interface EmptyAction extends Action {
    static Action get() {
        return () -> {};
    }
}