package com.thedistantblue.triaryapp.mainscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.thedistantblue.triaryapp.entities.base.User;

public class MainScreenFragmentStateAdapter extends FragmentStateAdapter {

    private final TitledFragment trainingFragment;
    private final TitledFragment runningFragment;

    public MainScreenFragmentStateAdapter(@NonNull MainScreenFragment mainScreenFragment,
                                          @NonNull User user) {
        super(mainScreenFragment);
        this.trainingFragment = TrainingListFragment.newInstance(user);
        this.runningFragment = RunningFragment.newInstance(user);
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
            return trainingFragment;
        }
        if (position == 1) {
            return runningFragment;
        }
        throw new IllegalArgumentException("No fragment for position " + position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}