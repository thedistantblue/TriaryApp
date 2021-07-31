package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import com.thedistantblue.triaryapp.database.room.dao.TrainingDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.base.Training;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrainingDaoProxy implements TrainingDao {
    private final TrainingDao trainingDao;

    @Override
    public Completable create(Training training) {
        return RxConfigurator.configureThreading(trainingDao.create(training));
    }

    @Override
    public Completable save(Training training) {
        return RxConfigurator.configureThreading(trainingDao.save(training));
    }

    @Override
    public Completable delete(Training training) {
        return RxConfigurator.configureThreading(trainingDao.delete(training));
    }

    @Override
    public Single<Training> findById(String trainingId) {
        return RxConfigurator.configureThreading(trainingDao.findById(trainingId));
    }

    @Override
    public Single<List<Training>> findAll() {
        return RxConfigurator.configureThreading(trainingDao.findAll());
    }
}