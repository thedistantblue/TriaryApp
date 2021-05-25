package com.thedistantblue.triaryapp.database.room.database.proxy.base;

import android.os.AsyncTask;

import com.thedistantblue.triaryapp.database.room.dao.base.UserDao;
import com.thedistantblue.triaryapp.entities.base.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDaoProxy implements UserDao {
    private final UserDao userDao;

    private void doInAsync(Consumer<User> action, User ex) {
        AsyncTask.execute(() -> action.accept(ex));
    }

    @Override
    public void create(User user) {
        doInAsync(userDao::create, user);
    }

    @Override
    public void save(User user) {
        doInAsync(userDao::save, user);
    }

    @Override
    public void delete(User user) {
        doInAsync(userDao::delete, user);
    }

    @Override
    public User findById(String exerciseSetId) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        AsyncTask.execute(() -> userAtomicReference.set(userDao.findById(exerciseSetId)));
        return userAtomicReference.get();
    }

    @Override
    public List<User> findAll() {
        AtomicReference<List<User>> userList = new AtomicReference<>();
        AsyncTask.execute(() -> userList.set(userDao.findAll()));
        return userList.get();
    }
}