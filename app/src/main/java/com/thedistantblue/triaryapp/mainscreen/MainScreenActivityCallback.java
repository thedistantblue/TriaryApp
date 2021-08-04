package com.thedistantblue.triaryapp.mainscreen;

import androidx.fragment.app.Fragment;

public interface MainScreenActivityCallback {
    void switchFragment(Fragment fragment);
    void setTitle(int title);
}