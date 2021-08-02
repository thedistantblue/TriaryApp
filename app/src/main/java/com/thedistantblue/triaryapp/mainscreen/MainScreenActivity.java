package com.thedistantblue.triaryapp.mainscreen;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.UserDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.database.room.database.utils.ObserverFactory;
import com.thedistantblue.triaryapp.entities.base.User;

public class MainScreenActivity extends AppCompatActivity implements MainScreenActivityCallback {

    private UserDao userDao;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_relative_layout);
        userDao = RoomDataBaseProvider.getDatabaseWithProxy(getApplicationContext()).userDao();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        fragmentManager = getSupportFragmentManager();

        userDao.findAll().subscribeWith(ObserverFactory.createSingleObserver((userList) -> {
            if (!userList.isEmpty()) {
                startApplication(userList.get(0));
            } else {
                startApplication(createUser());
            }
        }));
    }

    private void startApplication(User user) {
        fragmentManager.beginTransaction()
                       .add(R.id.fragment_container, MainScreenFragment.newInstance(user))
                       .commit();
    }

    public void switchFragment(TitledFragment fragment) {
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);

        getSupportActionBar().setTitle(fragment.getTitle());

        if (!fragmentPopped) {
            fragmentManager.beginTransaction()
                           .replace(R.id.fragment_container, fragment)
                           .addToBackStack(backStackName)
                           .commit();
        }
    }

    private User createUser() {
        User user = new User();
        userDao.create(user).subscribe();
        return user;
    }

}