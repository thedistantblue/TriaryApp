package com.thedistantblue.triaryapp.mainscreen;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thedistantblue.triaryapp.R;

import com.thedistantblue.triaryapp.database.room.dao.base.UserDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.entities.base.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainScreenActivity extends AppCompatActivity implements MainScreenActivityCallback {

    private static final String TRAINING_FRAGMENT_TAG = "tfTag";
    private static final String RUNNING_FRAGMENT_TAG = "rfTag";
    private static final String TRAINING_FRAGMENT_NAME = MainScreenActivity.class.getPackage() + "TrainingFragment";
    private static final String RUNNING_FRAGMENT_NAME = MainScreenActivity.class.getPackage() + "RunningFragment";

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
        BottomNavigationView nav = findViewById(R.id.tab_navigation);

        userDao.findAll()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe((userList -> {
                   if (!userList.isEmpty()) {
                       startApplication(userList.get(0), nav);
                   } else {
                       createUser();
                   }
               }));
    }

    private void startApplication(User user, BottomNavigationView nav) {
        manageFragments(TrainingFragment.newInstance(user), R.string.training_tab_button);

        nav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.switch_to_running_tab:
                    manageFragments(RunningFragment.newInstance(user), R.string.running_tab_button);
                    return true;
                case R.id.switch_to_trainings_tab:
                    manageFragments(TrainingFragment.newInstance(user), R.string.training_tab_button);
                    return true;
                default:
                    return MainScreenActivity.super.onOptionsItemSelected(menuItem);
            }
        });
    }

    public void manageFragments(Fragment fragment, int title) {
        String backStackName = fragment.getClass().getName();

        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);

        getSupportActionBar().setTitle(title);

        if (!fragmentPopped) {
            if (backStackName.equals(TRAINING_FRAGMENT_NAME)) {
                if (fragmentManager.findFragmentByTag(TRAINING_FRAGMENT_TAG)!= null) {
                    fragmentManager.beginTransaction()
                                   .replace(R.id.fragment_container, fragment, TRAINING_FRAGMENT_TAG)
                                   .commit();
                    return;
                }
                fragmentManager.beginTransaction()
                               .replace(R.id.fragment_container, fragment, TRAINING_FRAGMENT_TAG)
                               .addToBackStack(backStackName)
                               .commit();
            }

            if (backStackName.equals(RUNNING_FRAGMENT_NAME)) {
                if (fragmentManager.findFragmentByTag(RUNNING_FRAGMENT_TAG)!= null) {
                    fragmentManager.beginTransaction()
                                   .replace(R.id.fragment_container, fragment, RUNNING_FRAGMENT_TAG)
                                   .commit();
                    return;
                }
                fragmentManager.beginTransaction()
                               .replace(R.id.fragment_container, fragment, RUNNING_FRAGMENT_TAG)
                               .addToBackStack(backStackName)
                               .commit();
            }

            fragmentManager.beginTransaction()
                           .replace(R.id.fragment_container, fragment)
                           .addToBackStack(backStackName)
                           .commit();
        }
    }

    private void createUser() {
        User user = new User();
        userDao.create(user)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(() -> Log.d("CREATE_USER_TAG", "createUser: user created"));
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}