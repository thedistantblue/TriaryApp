package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import com.thedistantblue.triaryapp.database.room.dao.DatesDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.base.Dates;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DatesDaoProxy implements DatesDao {
    private final DatesDao datesDao;

    @Override
    public Completable create(Dates dates) {
        return RxConfigurator.configureThreading(datesDao.create(dates));
    }

    @Override
    public Completable save(Dates dates) {
        return RxConfigurator.configureThreading(datesDao.save(dates));
    }

    @Override
    public Completable delete(Dates dates) {
        return RxConfigurator.configureThreading(datesDao.delete(dates));
    }

    @Override
    public Single<Dates> findById(String datesId) {
        return RxConfigurator.configureThreading(datesDao.findById(datesId));
    }

    @Override
    public Single<List<Dates>> findAll() {
        return datesDao.findAll();
    }
}