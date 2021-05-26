package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.base.TrainingDao;
import com.thedistantblue.triaryapp.entities.base.Training;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrainingDaoProxy implements TrainingDao {
    private final TrainingDao trainingDao;

    @Override
    public Completable create(Training training) {
        return trainingDao.create(training);
    }

    @Override
    public Completable save(Training training) {
        return trainingDao.save(training);
    }

    @Override
    public Completable delete(Training training) {
        return trainingDao.delete(training);
    }

    @Override
    public Single<Training> findById(String trainingId) {
        return trainingDao.findById(trainingId);
    }

    @Override
    public Single<List<Training>> findAll() {
        return trainingDao.findAll();
    }
}