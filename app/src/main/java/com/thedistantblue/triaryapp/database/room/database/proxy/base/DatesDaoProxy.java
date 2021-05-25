package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.base.DatesDao;
import com.thedistantblue.triaryapp.entities.base.Dates;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DatesDaoProxy implements DatesDao {
    private final DatesDao datesDao;

    private void doInAsync(Consumer<Dates> action, Dates ex) {
        AsyncTask.execute(() -> action.accept(ex));
    }

    @Override
    public void create(Dates dates) {
        doInAsync(datesDao::create, dates);
    }

    @Override
    public void save(Dates dates) {
        doInAsync(datesDao::save, dates);
    }

    @Override
    public void delete(Dates dates) {
        doInAsync(datesDao::delete, dates);
    }

    @Override
    public Dates findById(String datesId) {
        AtomicReference<Dates> dates = new AtomicReference<>();
        AsyncTask.execute(() -> dates.set(datesDao.findById(datesId)));
        return dates.get();
    }

    @Override
    public List<Dates> findAll() {
        AtomicReference<List<Dates>> datesList = new AtomicReference<>();
        AsyncTask.execute(() -> datesList.set(datesDao.findAll()));
        return datesList.get();
    }
}