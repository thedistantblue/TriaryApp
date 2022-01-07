package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.DatePackCrossDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.base.DatePackCrossRef;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DatePackCrossDaoProxy implements DatePackCrossDao {
    private final DatePackCrossDao datePackCrossDao;

    @Override
    public Single<DatePackCrossRef> findById(String datesId) {
        return RxConfigurator.configureThreading(datePackCrossDao.findById(datesId));
    }

    @Override
    public Single<List<DatePackCrossRef>> findAll() {
        return RxConfigurator.configureThreading(datePackCrossDao.findAll());
    }

    @Override
    public Completable create(DatePackCrossRef entity) {
        return RxConfigurator.configureThreading(datePackCrossDao.create(entity));
    }

    @Override
    public Completable save(DatePackCrossRef entity) {
        return RxConfigurator.configureThreading(datePackCrossDao.save(entity));
    }

    @Override
    public Completable delete(DatePackCrossRef entity) {
        return RxConfigurator.configureThreading(datePackCrossDao.delete(entity));
    }
}