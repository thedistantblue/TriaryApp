package com.thedistantblue.triaryapp.database.room.database.utils;

import java.util.function.Consumer;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObserverFactory {
    public static <T> SingleObserver<T> createSingleObserver(Consumer<T> onSuccessConsumer) {
        return createSingleObserver(onSuccessConsumer, null);
    }

    public static <T> SingleObserver<T> createSingleObserver(Consumer<T> onSuccessConsumer, Consumer<Throwable> onErrorConsumer) {
        return new SingleObserver<T>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

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
}