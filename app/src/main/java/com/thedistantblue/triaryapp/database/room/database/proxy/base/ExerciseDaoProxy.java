package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.base.ExerciseDao;
import com.thedistantblue.triaryapp.entities.base.Exercise;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExerciseDaoProxy implements ExerciseDao {
    private final ExerciseDao exerciseDao;

    private void doInAsync(Consumer<Exercise> action, Exercise ex) {
        AsyncTask.execute(() -> action.accept(ex));
    }

    @Override
    public void create(Exercise exercise) {
        doInAsync(exerciseDao::create, exercise);
    }

    @Override
    public void save(Exercise exercise) {
        doInAsync(exerciseDao::save, exercise);
    }

    @Override
    public void delete(Exercise exercise) {
        doInAsync(exerciseDao::delete, exercise);
    }

    @Override
    public Exercise findById(String exerciseId) {
        AtomicReference<Exercise> exerciseAtomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> exerciseAtomicReference.set(exerciseDao.findById(exerciseId)));
        return exerciseAtomicReference.get();
    }

    @Override
    public List<Exercise> findAll() {
        AtomicReference<List<Exercise>> exerciseList = new AtomicReference<>();
        AsyncTask.execute(() -> exerciseList.set(exerciseDao.findAll()));
        return exerciseList.get();
    }
}