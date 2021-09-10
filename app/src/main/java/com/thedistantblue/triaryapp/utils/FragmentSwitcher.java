package com.thedistantblue.triaryapp.utils;

import com.thedistantblue.triaryapp.mainscreen.MainScreenActivity;
import com.thedistantblue.triaryapp.mainscreen.TitledFragment;

public class FragmentSwitcher {
    public static void showFragment(TitledFragment currentFragment, TitledFragment nextFragment) {
        if (currentFragment == null) {
            throw new IllegalStateException("Current fragment must not be null");
        }
        MainScreenActivity mainScreenActivity = (MainScreenActivity) currentFragment.getActivity();
        mainScreenActivity.switchFragment(nextFragment);
    }
}