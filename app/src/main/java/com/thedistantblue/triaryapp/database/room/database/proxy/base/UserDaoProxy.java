package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import com.thedistantblue.triaryapp.database.room.dao.UserDao;
import com.thedistantblue.triaryapp.entities.base.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDaoProxy implements UserDao {
    private final UserDao userDao;

    @Override
    public Completable create(User user) {
        return userDao.create(user);
    }

    @Override
    public Completable save(User user) {
        return userDao.save(user);
    }

    @Override
    public Completable delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public Single<User> findById(String userId) {
        return userDao.findById(userId);
    }

    @Override
    public Single<List<User>> findAll() {
        return userDao.findAll();
    }
}