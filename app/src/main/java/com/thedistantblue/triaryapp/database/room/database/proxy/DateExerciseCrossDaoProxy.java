package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.cross.DayExerciseCrossDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.cross.DayExerciseCrossRef;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DateExerciseCrossDaoProxy implements DayExerciseCrossDao {
    private final DayExerciseCrossDao dateExerciseCrossDao;

    @Override
    public Single<DayExerciseCrossRef> findById(String datesId) {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.findById(datesId));
    }

    @Override
    public Single<List<DayExerciseCrossRef>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.findAllById(ids));
    }

    @Override
    public Single<List<DayExerciseCrossRef>> findAll() {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.findAll());
    }

    @Override
    public Completable create(DayExerciseCrossRef entity) {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.create(entity));
    }

    @Override
    public Completable save(DayExerciseCrossRef entity) {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.save(entity));
    }

    @Override
    public Completable delete(DayExerciseCrossRef entity) {
        return RxConfigurator.configureThreading(dateExerciseCrossDao.delete(entity));
    }
}