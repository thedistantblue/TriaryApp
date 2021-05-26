package com.thedistantblue.triaryapp.database.room.database.proxy;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.entities.composite.ExerciseWithExerciseSet;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExerciseWithExerciseSetDaoProxy implements ExerciseWithExerciseSetDao {
    private final ExerciseWithExerciseSetDao exerciseWithExerciseSetDao;

    @Override
    public Single<ExerciseWithExerciseSet> findById(String exerciseId) {
        return exerciseWithExerciseSetDao.findById(exerciseId);
    }

    @Override
    public Single<List<ExerciseWithExerciseSet>> findAll() {
        return exerciseWithExerciseSetDao.findAll();
    }
}