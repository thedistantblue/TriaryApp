package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.base.ExerciseDao;
import com.thedistantblue.triaryapp.entities.base.Exercise;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExerciseDaoProxy implements ExerciseDao {
    private final ExerciseDao exerciseDao;

    @Override
    public Completable create(Exercise exercise) {
        return exerciseDao.create(exercise);
    }

    @Override
    public Completable save(Exercise exercise) {
        return exerciseDao.save(exercise);
    }

    @Override
    public Completable delete(Exercise exercise) {
        return exerciseDao.delete(exercise);
    }

    @Override
    public Single<Exercise> findById(String exerciseId) {
        return exerciseDao.findById(exerciseId);
    }

    @Override
    public Single<List<Exercise>> findAll() {
        return exerciseDao.findAll();
    }
}