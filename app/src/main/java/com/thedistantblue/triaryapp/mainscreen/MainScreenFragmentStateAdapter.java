package com.thedistantblue.triaryapp.mainscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainScreenFragmentStateAdapter extends FragmentStateAdapter {

    private final TitledFragment trainingFragment = new TrainingListFragment();
    private final TitledFragment runningFragment = new RunningFragment();

    public MainScreenFragmentStateAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        TitledFragment fragment = getFragmentForPosition(position);
        Bundle args = new Bundle();
        args.putInt(fragment.getName(), position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    private TitledFragment getFragmentForPosition(int position) {
        if (position == 0) {
            return runningFragment;
        }
        if (position == 1) {
            return trainingFragment;
        }
        throw new IllegalArgumentException("No fragment for position " + position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}