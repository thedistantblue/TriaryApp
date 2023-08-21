package com.thedistantblue.triaryapp.mainscreen;

import static com.thedistantblue.triaryapp.utils.BundleKeyConstants.USER_KEY;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.room.dao.UserDao;
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider;
import com.thedistantblue.triaryapp.database.room.database.utils.ObserverFactory;
import com.thedistantblue.triaryapp.entities.base.User;

@SuppressWarnings("ConstantConditions")
public class MainScreenActivityImpl extends AppCompatActivity implements MainScreenActivity {

    public static FragmentManager fragmentManager;

    private UserDao userDao;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_relative_layout);
        initDaos();
        fragmentManager = getSupportFragmentManager();
        drawerLayout = findViewById(R.id.drawer_layout);

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

    private void startApplication(@NonNull User user) {
        MainScreenFragment mainScreenFragment = MainScreenFragment.newInstance(user);
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);

        NavHostFragment navHostFragment
                = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        navController.setGraph(R.navigation.main_nav_graph, args);

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .setOpenableLayout(drawerLayout)
                        .build();
        setupActionBar(appBarConfiguration);

        actionBar.setTitle(mainScreenFragment.getTitle());
    }

    private void setupActionBar(AppBarConfiguration appBarConfiguration) {
        Toolbar toolBar = findViewById(R.id.toolbar);
        toolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolBar);
        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        NavigationUI.setupWithNavController(toolBar, navController, appBarConfiguration);
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
                           .replace(R.id.nav_host_fragment, fragment)
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
        //todo: временное решение, которое на самом деле ничего не решает
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment)
                                                  .getChildFragmentManager()
                                                  .getFragments()
                                                  .get(0);

        TitledFragment titledFragment = (TitledFragment) currentFragment;
        if (titledFragment != null) {
            actionBar.setTitle(titledFragment.getTitle());
        }
    }
}