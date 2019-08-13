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

// Здесь надо задать текст аппбара, а также обрабатывать нажатия на
// итемы нижнего меню.
// Надо ли здесь использовать датабиндинг?
public class MainScreenActivity extends AppCompatActivity {

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
        //Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        BottomNavigationView nav = findViewById(R.id.tab_navigation);

        manageFragments(TrainingFragment.newInstance(user), "Training");

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.switch_to_running_tab:
                        Toast.makeText(getApplicationContext(), "Running tab", Toast.LENGTH_SHORT).show();
                        manageFragments(RunningFragment.newInstance(user), "Running");
                        return true;
                    case R.id.switch_to_trainings_tab:
                        Toast.makeText(getApplicationContext(), "Training tab", Toast.LENGTH_SHORT).show();
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
