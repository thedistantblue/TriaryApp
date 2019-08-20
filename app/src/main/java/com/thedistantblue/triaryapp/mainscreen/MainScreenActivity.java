package com.thedistantblue.triaryapp.mainscreen;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thedistantblue.triaryapp.R;

import com.thedistantblue.triaryapp.entities.User;

import java.util.List;

public class MainScreenActivity extends AppCompatActivity implements ActivityCallback {

    FragmentManager fragmentManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_relative_layout);

        // Надо брать юзера из базы, если она есть, а не создавать
        // каждый раз нового с одним и тем же id
        final User user = new User(1);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TriaryApp");

        fragmentManager = getSupportFragmentManager();

        BottomNavigationView nav = findViewById(R.id.tab_navigation);

        manageFragments(TrainingFragment.newInstance(user), "Training");

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.switch_to_running_tab:
                        manageFragments(RunningFragment.newInstance(user), "Running");
                        return true;
                    case R.id.switch_to_trainings_tab:
                        manageFragments(TrainingFragment.newInstance(user), "Training");
                        return true;
                    default:
                        return MainScreenActivity.super.onOptionsItemSelected(menuItem);
                }
            }
        });
    }

    public void manageFragments(Fragment fragment, String title) {
        String backStackName = fragment.getClass().getName();
        String trainingFragmentName = "com.thedistantblue.triaryapp.mainscreen.TrainingFragment";
        String runningFragmentName = "com.thedistantblue.triaryapp.mainscreen.RunningFragment";

        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);

        /*
        if (backStackName.equals("com.thedistantblue.triaryapp.mainscreen.TrainingFlow.ExerciseListFragment")) {
            getSupportActionBar().setTitle("test123");
        }
        */

        if (title != null) {
            getSupportActionBar().setTitle(title);
        }


        if (!fragmentPopped) {
            if (backStackName.equals(trainingFragmentName)) {
                if (fragmentManager.findFragmentByTag("tf")!= null) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment, "tf")
                            .commit();
                    return;
                }
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment, "tf")
                        .addToBackStack(backStackName)
                        .commit();
            }

            if (backStackName.equals(runningFragmentName)) {
                if (fragmentManager.findFragmentByTag("rf")!= null) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment, "rf")
                            .commit();
                    return;
                }
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment, "rf")
                        .addToBackStack(backStackName)
                        .commit();
            }

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(backStackName)
                    .commit();
        }
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
