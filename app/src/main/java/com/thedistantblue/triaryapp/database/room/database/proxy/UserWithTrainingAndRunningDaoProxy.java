package com.thedistantblue.triaryapp.database.room.database.proxy;

import com.thedistantblue.triaryapp.database.room.dao.UserWithTrainingAndRunningDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.composite.UserWithTrainingAndRunning;

import java.util.Collection;
import java.util.List;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserWithTrainingAndRunningDaoProxy implements UserWithTrainingAndRunningDao {
    private final UserWithTrainingAndRunningDao userWithTrainingAndRunningDao;

    @Override
    public Single<UserWithTrainingAndRunning> findById(String userId) {
        return RxConfigurator.configureThreading(userWithTrainingAndRunningDao.findById(userId));
    }

    @Override
    public Single<List<UserWithTrainingAndRunning>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(userWithTrainingAndRunningDao.findAllById(ids));
    }

    @Override
    public Single<List<UserWithTrainingAndRunning>> findAll() {
        return RxConfigurator.configureThreading(userWithTrainingAndRunningDao.findAll());
    }
}