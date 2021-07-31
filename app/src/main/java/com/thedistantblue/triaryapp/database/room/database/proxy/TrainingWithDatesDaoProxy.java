package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.TrainingWithDatesDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.composite.TrainingWithDates;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrainingWithDatesDaoProxy implements TrainingWithDatesDao {
    private final TrainingWithDatesDao trainingWithDatesDao;

    @Override
    public Single<TrainingWithDates> findById(String trainingId) {
        return RxConfigurator.configureThreading(trainingWithDatesDao.findById(trainingId));
    }

    @Override
    public Single<List<TrainingWithDates>> findAll() {
        return RxConfigurator.configureThreading(trainingWithDatesDao.findAll());
    }
}