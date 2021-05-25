package com.thedistantblue.triaryapp.database.room.database.proxy;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.DatesWithExerciseDao;
import com.thedistantblue.triaryapp.entities.composite.DatesWithExercise;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DatesWithExerciseDaoProxy implements DatesWithExerciseDao {
    private final DatesWithExerciseDao datesWithExerciseDao;

    @Override
    public DatesWithExercise findById(String datesId) {
        AtomicReference<DatesWithExercise> datesWithExerciseAtomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> datesWithExerciseAtomicReference.set(datesWithExerciseDao.findById(datesId)));
        return datesWithExerciseAtomicReference.get();
    }

    @Override
    public List<DatesWithExercise> findAll() {
        AtomicReference<List<DatesWithExercise>> datesWithExerciseListAtomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> datesWithExerciseListAtomicReference.set(datesWithExerciseDao.findAll()));
        return datesWithExerciseListAtomicReference.get();
    }
}