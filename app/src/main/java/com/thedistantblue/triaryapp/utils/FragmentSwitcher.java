package com.thedistantblue.triaryapp.utils;

import androidx.fragment.app.Fragment;

import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;

public class FragmentSwitcher {
    public static void showFragment(Fragment currentFragment, Fragment nextFragment, int nextFragmentTitle) {
        if (currentFragment == null) {
            throw new IllegalStateException("Current fragment must not be null");
        }
        MainScreenActivityCallback mainScreenActivity = (MainScreenActivityCallback) currentFragment.getActivity();
        mainScreenActivity.manageFragments(nextFragment, nextFragmentTitle);
    }
}