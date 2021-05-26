package com.thedistantblue.triaryapp.database.room.database.proxy;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.DatesWithExerciseDao;
import com.thedistantblue.triaryapp.entities.composite.DatesWithExercise;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DatesWithExerciseDaoProxy implements DatesWithExerciseDao {
    private final DatesWithExerciseDao datesWithExerciseDao;

    @Override
    public Single<DatesWithExercise> findById(String datesId) {
        return datesWithExerciseDao.findById(datesId);
    }

    @Override
    public Single<List<DatesWithExercise>> findAll() {
        return datesWithExerciseDao.findAll();
    }
}