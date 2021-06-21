package com.thedistantblue.triaryapp.database.room.database.utils;

import java.util.function.Consumer;

public interface AutoDisposableSingle<T> {
    void subscribe(Consumer<? super T> onSuccess);
}