package com.thedistantblue.triaryapp.database.room.database.proxy;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.entities.composite.UserWithTrainingAndRunning;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserWithTrainingAndRunningDaoProxy implements UserWithTrainingAndRunningDao {
    private final UserWithTrainingAndRunningDao userWithTrainingAndRunningDao;

    @Override
    public UserWithTrainingAndRunning findById(String datesId) {
        AtomicReference<UserWithTrainingAndRunning> atomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> atomicReference.set(userWithTrainingAndRunningDao.findById(datesId)));
        return atomicReference.get();
    }

    @Override
    public List<UserWithTrainingAndRunning> findAll() {
        AtomicReference<List<UserWithTrainingAndRunning>> atomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> atomicReference.set(userWithTrainingAndRunningDao.findAll()));
        return atomicReference.get();
    }
}