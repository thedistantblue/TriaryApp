package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.details.ExerciseDetailsDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExerciseDetailsDaoProxy implements ExerciseDetailsDao {
    private final ExerciseDetailsDao exerciseDetailsDao;

    @Override
    public Single<ExerciseDetails> findById(String exerciseId) {
        return RxConfigurator.configureThreading(exerciseDetailsDao.findById(exerciseId));
    }

    @Override
    public Single<List<ExerciseDetails>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(exerciseDetailsDao.findAllById(ids));
    }

    @Override
    public Single<List<ExerciseDetails>> findAllByTrainingId(String trainingId) {
        return RxConfigurator.configureThreading(exerciseDetailsDao.findAllByTrainingId(trainingId));
    }

    @Override
    public Single<List<ExerciseDetails>> findAll() {
        return RxConfigurator.configureThreading(exerciseDetailsDao.findAll());
    }
}