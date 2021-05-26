package com.thedistantblue.triaryapp.database.room.database.proxy;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.entities.composite.UserWithTrainingAndRunning;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserWithTrainingAndRunningDaoProxy implements UserWithTrainingAndRunningDao {
    private final UserWithTrainingAndRunningDao userWithTrainingAndRunningDao;

    @Override
    public Single<UserWithTrainingAndRunning> findById(String userId) {
        return userWithTrainingAndRunningDao.findById(userId);
    }

    @Override
    public Single<List<UserWithTrainingAndRunning>> findAll() {
        return userWithTrainingAndRunningDao.findAll();
    }
}