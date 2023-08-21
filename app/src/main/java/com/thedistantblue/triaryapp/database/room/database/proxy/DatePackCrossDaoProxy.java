package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.cross.DayPackCrossDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.cross.DayPackCrossRef;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DatePackCrossDaoProxy implements DayPackCrossDao {
    private final DayPackCrossDao datePackCrossDao;

    @Override
    public Single<DayPackCrossRef> findById(String datesId) {
        return RxConfigurator.configureThreading(datePackCrossDao.findById(datesId));
    }

    @Override
    public Single<List<DayPackCrossRef>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(datePackCrossDao.findAllById(ids));
    }

    @Override
    public Single<List<DayPackCrossRef>> findAll() {
        return RxConfigurator.configureThreading(datePackCrossDao.findAll());
    }

    @Override
    public Completable create(DayPackCrossRef entity) {
        return RxConfigurator.configureThreading(datePackCrossDao.create(entity));
    }

    @Override
    public Completable save(DayPackCrossRef entity) {
        return RxConfigurator.configureThreading(datePackCrossDao.save(entity));
    }

    @Override
    public Completable delete(DayPackCrossRef entity) {
        return RxConfigurator.configureThreading(datePackCrossDao.delete(entity));
    }
}