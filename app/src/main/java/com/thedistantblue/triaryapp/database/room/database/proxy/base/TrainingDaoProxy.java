package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.base.TrainingDao;
import com.thedistantblue.triaryapp.entities.base.Training;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrainingDaoProxy implements TrainingDao {
    private final TrainingDao trainingDao;

    private void doInAsync(Consumer<Training> action, Training ex) {
        AsyncTask.execute(() -> action.accept(ex));
    }

    @Override
    public void create(Training training) {
        doInAsync(trainingDao::create, training);
    }

    @Override
    public void save(Training training) {
        doInAsync(trainingDao::save, training);
    }

    @Override
    public void delete(Training training) {
        doInAsync(trainingDao::delete, training);
    }

    @Override
    public Training findById(String exerciseSetId) {
        AtomicReference<Training> trainingAtomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> trainingAtomicReference.set(trainingDao.findById(exerciseSetId)));
        return trainingAtomicReference.get();
    }

    @Override
    public List<Training> findAll() {
        AtomicReference<List<Training>> trainingList = new AtomicReference<>();
        AsyncTask.execute(() -> trainingList.set(trainingDao.findAll()));
        return trainingList.get();
    }
}