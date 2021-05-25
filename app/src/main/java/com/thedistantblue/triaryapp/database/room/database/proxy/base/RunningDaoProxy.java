package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.base.RunningDao;
import com.thedistantblue.triaryapp.entities.base.Running;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RunningDaoProxy implements RunningDao {
    private final RunningDao runningDao;

    private void doInAsync(Consumer<Running> action, Running ex) {
        AsyncTask.execute(() -> action.accept(ex));
    }

    @Override
    public void create(Running running) {
        doInAsync(runningDao::create, running);
    }

    @Override
    public void save(Running running) {
        doInAsync(runningDao::save, running);
    }

    @Override
    public void delete(Running running) {
        doInAsync(runningDao::delete, running);
    }

    @Override
    public Running findById(String exerciseSetId) {
        AtomicReference<Running> runningAtomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> runningAtomicReference.set(runningDao.findById(exerciseSetId)));
        return runningAtomicReference.get();
    }

    @Override
    public List<Running> findAll() {
        AtomicReference<List<Running>> runningList = new AtomicReference<>();
        AsyncTask.execute(() -> runningList.set(runningDao.findAll()));
        return runningList.get();
    }
}