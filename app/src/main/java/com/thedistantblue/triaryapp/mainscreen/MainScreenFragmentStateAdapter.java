package com.thedistantblue.triaryapp.mainscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.thedistantblue.triaryapp.entities.base.User;
import com.thedistantblue.triaryapp.mainscreen.RunningFlow.RunningListFragment;
import com.thedistantblue.triaryapp.mainscreen.TrainingFlow.TrainingListFragment;

public class MainScreenFragmentStateAdapter extends FragmentStateAdapter {

    private final AutoDisposableFragment trainingFragment;
    private final AutoDisposableFragment runningFragment;

    public MainScreenFragmentStateAdapter(@NonNull MainScreenFragment mainScreenFragment,
                                          @NonNull User user) {
        super(mainScreenFragment);
        this.trainingFragment = TrainingListFragment.newInstance(user);
        this.runningFragment = RunningListFragment.newInstance(user);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        AutoDisposableFragment fragment = getFragmentForPosition(position);
        Bundle args = new Bundle();
        args.putInt(fragment.getName(), position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    private AutoDisposableFragment getFragmentForPosition(int position) {
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