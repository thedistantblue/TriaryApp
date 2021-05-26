package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.base.ExerciseSetDao;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExerciseSetDaoProxy implements ExerciseSetDao {
    private final ExerciseSetDao exerciseSetDao;

    @Override
    public Completable create(ExerciseSet exerciseSet) {
        return exerciseSetDao.create(exerciseSet);
    }

    @Override
    public Completable save(ExerciseSet exerciseSet) {
        return exerciseSetDao.save(exerciseSet);
    }

    @Override
    public Completable delete(ExerciseSet exerciseSet) {
        return exerciseSetDao.delete(exerciseSet);
    }

    @Override
    public Single<ExerciseSet> findById(String exerciseSetId) {
        return exerciseSetDao.findById(exerciseSetId);
    }

    @Override
    public Single<List<ExerciseSet>> findAll() {
        return exerciseSetDao.findAll();
    }
}