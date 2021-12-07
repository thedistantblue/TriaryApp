package com.thedistantblue.triaryapp.mainscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.entities.base.User;

public class MainScreenFragment extends NavHostFragment {

    private static final String USER_KEY = "user";

    private ViewPager2 viewPager;
    private MainScreenFragmentStateAdapter stateAdapter;

    public static MainScreenFragment newInstance(@NonNull User user) {
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY, user);

        MainScreenFragment fragment = new MainScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_screen_tab_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        User user = (User) requireArguments().getSerializable(USER_KEY);
        stateAdapter = new MainScreenFragmentStateAdapter(this, user);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(stateAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout,
                              viewPager,
                              this::setTabTitle)
                .attach();
    }

    private void setTabTitle(TabLayout.Tab tab, int position) {
        if (position == 0) {
            tab.setText(R.string.training_tab_button);
        }
        if (position == 1) {
            tab.setText(R.string.running_tab_button);
        }
    }

    public int getTitle() {
        return R.string.main_screen_fragment_workouts;
    }
}