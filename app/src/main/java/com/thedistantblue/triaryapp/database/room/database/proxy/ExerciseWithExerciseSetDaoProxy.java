package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.composite.ExerciseWithExerciseSet;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExerciseWithExerciseSetDaoProxy implements ExerciseWithExerciseSetDao {
    private final ExerciseWithExerciseSetDao exerciseWithExerciseSetDao;

    @Override
    public Single<ExerciseWithExerciseSet> findById(String exerciseId) {
        return RxConfigurator.configureThreading(exerciseWithExerciseSetDao.findById(exerciseId));
    }

    @Override
    public Single<List<ExerciseWithExerciseSet>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(exerciseWithExerciseSetDao.findAllById(ids));
    }

    @Override
    public Single<List<ExerciseWithExerciseSet>> findAll() {
        return RxConfigurator.configureThreading(exerciseWithExerciseSetDao.findAll());
    }
}