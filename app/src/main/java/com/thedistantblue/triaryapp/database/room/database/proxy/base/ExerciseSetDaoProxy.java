package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.base.ExerciseSetDao;
import com.thedistantblue.triaryapp.entities.base.ExerciseSet;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExerciseSetDaoProxy implements ExerciseSetDao {
    private final ExerciseSetDao exerciseSetDao;

    private void doInAsync(Consumer<ExerciseSet> action, ExerciseSet ex) {
        AsyncTask.execute(() -> action.accept(ex));
    }

    @Override
    public void create(ExerciseSet exerciseSet) {
        doInAsync(exerciseSetDao::create, exerciseSet);
    }

    @Override
    public void save(ExerciseSet exerciseSet) {
        doInAsync(exerciseSetDao::save, exerciseSet);
    }

    @Override
    public void delete(ExerciseSet exerciseSet) {
        doInAsync(exerciseSetDao::delete, exerciseSet);
    }

    @Override
    public ExerciseSet findById(String exerciseSetId) {
        AtomicReference<ExerciseSet> exerciseAtomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> exerciseAtomicReference.set(exerciseSetDao.findById(exerciseSetId)));
        return exerciseAtomicReference.get();
    }

    @Override
    public List<ExerciseSet> findAll() {
        AtomicReference<List<ExerciseSet>> exerciseSetList = new AtomicReference<>();
        AsyncTask.execute(() -> exerciseSetList.set(exerciseSetDao.findAll()));
        return exerciseSetList.get();
    }
}