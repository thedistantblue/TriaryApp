package com.thedistantblue.triaryapp.mainscreen.power.detail;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.thedistantblue.triaryapp.R;

public class PowerTrainingDetailActivity extends AppCompatActivity {

    private NavController navController;

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
                = (NavHostFragment) fragmentManager.findFragmentById(R.id.tab_power_nav_host);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            navController.setGraph(R.navigation.power_training_detail_nav_graph, extras);
        }
    }

    //todo сразу выкидывает на главный экран после компоузов, будет исправлено, когда вся навигация тут
    // перейдет на компоуз
//    @Override
//    public void onBackPressed() {
//        if (!navController.popBackStack()) {
//            super.onBackPressed();
//        }
//    }
}