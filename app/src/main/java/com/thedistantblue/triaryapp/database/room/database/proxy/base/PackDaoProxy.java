package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import com.thedistantblue.triaryapp.database.room.dao.PackDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.base.Pack;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PackDaoProxy implements PackDao {
    private final PackDao packDao;

    @Override
    public Completable create(Pack pack) {
        return RxConfigurator.configureThreading(packDao.create(pack));
    }

    @Override
    public Completable save(Pack pack) {
        return RxConfigurator.configureThreading(packDao.save(pack));
    }

    @Override
    public Completable delete(Pack pack) {
        return RxConfigurator.configureThreading(packDao.delete(pack));
    }

    @Override
    public Single<Pack> findById(String packId) {
        return RxConfigurator.configureThreading(packDao.findById(packId));
    }

    @Override
    public Single<List<Pack>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(packDao.findAllById(ids));
    }

    @Override
    public Single<List<Pack>> findAll() {
        return RxConfigurator.configureThreading(packDao.findAll());
    }
}