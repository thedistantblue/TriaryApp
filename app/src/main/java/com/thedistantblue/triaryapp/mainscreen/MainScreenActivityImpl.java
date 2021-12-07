package com.thedistantblue.triaryapp.mainscreen;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.UserDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.database.room.database.utils.ObserverFactory;
import com.thedistantblue.triaryapp.entities.base.User;

@SuppressWarnings("ConstantConditions")
public class MainScreenActivityImpl extends AppCompatActivity implements MainScreenActivity {

    private UserDao userDao;
    private ActionBar actionBar;
    private FragmentManager fragmentManager;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_relative_layout);
        initDaos();
        setupActionBar();
        fragmentManager = getSupportFragmentManager();
        //navController = Navigation.findNavController(this, R.id.fragment_container);

        userDao.findAll().subscribeWith(ObserverFactory.createSingleObserver((userList) -> {
            if (!userList.isEmpty()) {
                startApplication(userList.get(0));
            } else {
                startApplication(createUser());
            }
        }));
    }

    private void initDaos() {
        userDao = RoomDataBaseProvider.getDatabaseWithProxy(getApplicationContext()).userDao();
    }

    private void setupActionBar() {
        Toolbar toolBar = findViewById(R.id.toolbar);
        toolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolBar);
        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
    }

    private void startApplication(@NonNull User user) {
        MainScreenFragment mainScreenFragment = MainScreenFragment.newInstance(user);
        fragmentManager.beginTransaction()
                       .add(R.id.fragment_container, mainScreenFragment)
                       .commit();
        actionBar.setTitle(mainScreenFragment.getTitle());
    }

    @Override
    public void switchFragment(Fragment fragment) {
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);

        if (fragment instanceof TitledFragment) {
            actionBar.setTitle(((TitledFragment) fragment).getTitle());
        } else if (fragment instanceof MainScreenFragment) {
            actionBar.setTitle(((MainScreenFragment) fragment).getTitle());
        }

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (!(fragment instanceof MainScreenFragment)) {
            TitledFragment titledFragment = (TitledFragment) fragment;
            if (titledFragment != null) {
                actionBar.setTitle(titledFragment.getTitle());
            }
        } else {
            MainScreenFragment mainScreenFragment = (MainScreenFragment) fragment;
            actionBar.setTitle(mainScreenFragment.getTitle());
        }
    }
}