package com.thedistantblue.triaryapp.database.room.database.proxy;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.ExerciseWithExerciseSetDao;
import com.thedistantblue.triaryapp.entities.composite.ExerciseWithExerciseSet;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExerciseWithExerciseSetDaoProxy implements ExerciseWithExerciseSetDao {
    private final ExerciseWithExerciseSetDao exerciseWithExerciseSetDao;

    @Override
    public ExerciseWithExerciseSet findById(String datesId) {
        AtomicReference<ExerciseWithExerciseSet> atomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> atomicReference.set(exerciseWithExerciseSetDao.findById(datesId)));
        return atomicReference.get();
    }

    @Override
    public List<ExerciseWithExerciseSet> findAll() {
        AtomicReference<List<ExerciseWithExerciseSet>> atomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> atomicReference.set(exerciseWithExerciseSetDao.findAll()));
        return atomicReference.get();
    }
}