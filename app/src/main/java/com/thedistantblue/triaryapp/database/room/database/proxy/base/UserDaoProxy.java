package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import com.thedistantblue.triaryapp.database.room.dao.UserDao;
import com.thedistantblue.triaryapp.database.room.database.utils.RxConfigurator;
import com.thedistantblue.triaryapp.entities.base.User;

import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDaoProxy implements UserDao {
    private final UserDao userDao;

    @Override
    public Completable create(User user) {
        return RxConfigurator.configureThreading(userDao.create(user));
    }

    @Override
    public Completable save(User user) {
        return RxConfigurator.configureThreading(userDao.save(user));
    }

    @Override
    public Completable delete(User user) {
        return RxConfigurator.configureThreading(userDao.delete(user));
    }

    @Override
    public Single<User> findById(String userId) {
        return RxConfigurator.configureThreading(userDao.findById(userId));
    }

    @Override
    public Single<List<User>> findAllById(Collection<String> ids) {
        return RxConfigurator.configureThreading(userDao.findAllById(ids));
    }

    @Override
    public Single<List<User>> findAll() {
        return RxConfigurator.configureThreading(userDao.findAll());
    }
}