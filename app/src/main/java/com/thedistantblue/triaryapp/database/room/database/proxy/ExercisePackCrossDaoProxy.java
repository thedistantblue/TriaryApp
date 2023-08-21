package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.cross.ExercisePackCrossDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.cross.ExercisePackCrossRef;

import java.util.Collection;
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
    public Single<List<ExercisePackCrossRef>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(exercisePackCrossDao.findAllById(ids));
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