package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import com.thedistantblue.triaryapp.database.room.dao.DayDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.base.Day;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DayDaoProxy implements DayDao {
    private final DayDao dayDao;

    @Override
    public Completable create(Day dates) {
        return RxConfigurator.configureThreading(dayDao.create(dates));
    }

    @Override
    public Completable save(Day dates) {
        return RxConfigurator.configureThreading(dayDao.save(dates));
    }

    @Override
    public Completable delete(Day dates) {
        return RxConfigurator.configureThreading(dayDao.delete(dates));
    }

    @Override
    public Single<Day> findById(String datesId) {
        return RxConfigurator.configureThreading(dayDao.findById(datesId));
    }

    @Override
    public Single<List<Day>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(dayDao.findAllById(ids));
    }

    @Override
    public Single<List<Day>> findAll() {
        return dayDao.findAll();
    }
}