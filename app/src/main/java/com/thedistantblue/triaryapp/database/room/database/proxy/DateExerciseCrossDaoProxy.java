package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.DateExerciseCrossDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.base.DateExerciseCrossRef;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DateExerciseCrossDaoProxy implements DateExerciseCrossDao {
    private final DateExerciseCrossDao dateExerciseCrossDao;

    @Override
    public Single<DateExerciseCrossRef> findById(String datesId) {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.findById(datesId));
    }

    @Override
    public Single<List<DateExerciseCrossRef>> findAll() {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.findAll());
    }

    @Override
    public Completable create(DateExerciseCrossRef entity) {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.create(entity));
    }

    @Override
    public Completable save(DateExerciseCrossRef entity) {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.save(entity));
    }

    @Override
    public Completable delete(DateExerciseCrossRef entity) {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.delete(entity));
    }
}