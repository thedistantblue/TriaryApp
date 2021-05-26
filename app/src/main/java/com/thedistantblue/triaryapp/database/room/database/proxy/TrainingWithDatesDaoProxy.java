package com.thedistantblue.triaryapp.database.room.database.proxy;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.TrainingWithDatesDao;
import com.thedistantblue.triaryapp.entities.composite.TrainingWithDates;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrainingWithDatesDaoProxy implements TrainingWithDatesDao {
    private final TrainingWithDatesDao trainingWithDatesDao;

    @Override
    public Single<TrainingWithDates> findById(String trainingId) {
        return trainingWithDatesDao.findById(trainingId);
    }

    @Override
    public Single<List<TrainingWithDates>> findAll() {
        return trainingWithDatesDao.findAll();
    }
}