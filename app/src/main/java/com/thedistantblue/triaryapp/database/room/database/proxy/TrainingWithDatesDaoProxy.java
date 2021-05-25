package com.thedistantblue.triaryapp.database.room.database.proxy;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.TrainingWithDatesDao;
import com.thedistantblue.triaryapp.entities.composite.TrainingWithDates;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrainingWithDatesDaoProxy implements TrainingWithDatesDao {
    private final TrainingWithDatesDao trainingWithDatesDao;

    @Override
    public TrainingWithDates findById(String datesId) {
        AtomicReference<TrainingWithDates> atomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> atomicReference.set(trainingWithDatesDao.findById(datesId)));
        return atomicReference.get();
    }

    @Override
    public List<TrainingWithDates> findAll() {
        AtomicReference<List<TrainingWithDates>> atomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> atomicReference.set(trainingWithDatesDao.findAll()));
        return atomicReference.get();
    }
}