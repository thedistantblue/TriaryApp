package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.base.RunningDao;
import com.thedistantblue.triaryapp.entities.base.Running;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RunningDaoProxy implements RunningDao {
    private final RunningDao runningDao;

    @Override
    public Completable create(Running running) {
        return runningDao.create(running);
    }

    @Override
    public Completable save(Running running) {
        return runningDao.save(running);
    }

    @Override
    public Completable delete(Running running) {
        return runningDao.delete(running);
    }

    @Override
    public Single<Running> findById(String runningId) {
        return runningDao.findById(runningId);
    }

    @Override
    public Single<List<Running>> findAll() {
        return runningDao.findAll();
    }
}