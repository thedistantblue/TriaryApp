package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.ExercisePackCrossDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.base.ExercisePackCrossRef;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExercisePackCrossDaoProxy implements ExercisePackCrossDao {
    private final ExercisePackCrossDao exercisePackCrossDao;

    @Override
    public Single<ExercisePackCrossRef> findById(String exerciseId) {
        return RxConfigurator.configureThreading(exercisePackCrossDao.findById(exerciseId));
    }

    @Override
    public Single<List<ExercisePackCrossRef>> findAll() {
        return RxConfigurator.configureThreading(exercisePackCrossDao.findAll());
    }

    @Override
    public Completable create(ExercisePackCrossRef entity) {
        return RxConfigurator.configureThreading(exercisePackCrossDao.create(entity));
    }

    @Override
    public Completable save(ExercisePackCrossRef entity) {
        return RxConfigurator.configureThreading(exercisePackCrossDao.save(entity));
    }

    @Override
    public Completable delete(ExercisePackCrossRef entity) {
        return RxConfigurator.configureThreading(exercisePackCrossDao.delete(entity));
    }
}