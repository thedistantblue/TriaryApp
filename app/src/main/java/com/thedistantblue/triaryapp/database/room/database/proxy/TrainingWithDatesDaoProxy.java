package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.TrainingWithDaysDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.composite.TrainingWithDays;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrainingWithDatesDaoProxy implements TrainingWithDaysDao {
    private final TrainingWithDaysDao trainingWithDatesDao;

    @Override
    public Single<TrainingWithDays> findById(String trainingId) {
        return RxConfigurator.configureThreading(trainingWithDatesDao.findById(trainingId));
    }

    @Override
    public Single<List<TrainingWithDays>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(trainingWithDatesDao.findAllById(ids));
    }

    @Override
    public Single<List<TrainingWithDays>> findAll() {
        return RxConfigurator.configureThreading(trainingWithDatesDao.findAll());
    }
}