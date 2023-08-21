package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.details.PackDetailsDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.composite.details.PackDetails;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PackDetailsDaoProxy implements PackDetailsDao {
    private final PackDetailsDao packDetailsDao;

    @Override
    public Single<PackDetails> findById(String packId) {
        return RxConfigurator.configureThreading(packDetailsDao.findById(packId));
    }

    @Override
    public Single<List<PackDetails>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(packDetailsDao.findAllById(ids));
    }

    @Override
    public Single<List<PackDetails>> findAllByTrainingId(String trainingId) {
        return RxConfigurator.configureThreading(packDetailsDao.findAllByTrainingId(trainingId));
    }

    @Override
    public Single<List<PackDetails>> findAll() {
        return RxConfigurator.configureThreading(packDetailsDao.findAll());
    }
}