package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.TrainingDetailsDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.composite.details.TrainingDetails;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrainingDetailsDaoProxy implements TrainingDetailsDao {
    private final TrainingDetailsDao trainingDetailsDao;

    @Override
    public Single<TrainingDetails> findById(String trainingId) {
        return RxConfigurator.configureThreading(trainingDetailsDao.findById(trainingId));
    }

    @Override
    public Single<List<TrainingDetails>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(trainingDetailsDao.findAllById(ids));
    }

    @Override
    public Single<List<TrainingDetails>> findAll() {
        return RxConfigurator.configureThreading(trainingDetailsDao.findAll());
    }
}