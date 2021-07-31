package com.thedistantblue.triaryapp.utils;

import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityCallback;
import com.thedistantblue.triaryapp.mainscreen.TitledFragment;

public class FragmentSwitcher {
    public static void showFragment(TitledFragment currentFragment, TitledFragment nextFragment) {
        if (currentFragment == null) {
            throw new IllegalStateException("Current fragment must not be null");
        }
        MainScreenActivityCallback mainScreenActivity = (MainScreenActivityCallback) currentFragment.getActivity();
        mainScreenActivity.switchFragment(nextFragment);
    }
}