package com.thedistantblue.triaryapp.mainscreen.cardio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.thedistantblue.triaryapp.R;

public class RunningDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_power_nav_host_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        initNavigation(fragmentManager);
    }

    private void initNavigation(FragmentManager fragmentManager) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        NavHostFragment navHostFragment
                = (NavHostFragment) fragmentManager.findFragmentById(R.id.tab_cardio_nav_host);
        NavController navController = navHostFragment.getNavController();
        navController.setGraph(R.navigation.tab_cardio_nav_graph, extras);
    }

}