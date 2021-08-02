package com.thedistantblue.triaryapp.mainscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.thedistantblue.triaryapp.R;

public class MainScreenFragment extends TitledFragment {

    private ViewPager2 viewPager;
    private MainScreenFragmentStateAdapter stateAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_screen_tab_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        stateAdapter = new MainScreenFragmentStateAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(stateAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout,
                              viewPager,
                              (tab, position) -> tab.setText("OBJECT " + position))
                .attach();
    }

    @Override
    public int getTitle() {
        return R.string.running_tab_button;
    }
}