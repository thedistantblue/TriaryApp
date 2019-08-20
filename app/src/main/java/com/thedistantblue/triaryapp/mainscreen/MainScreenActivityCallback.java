package com.thedistantblue.triaryapp.mainscreen;

import androidx.fragment.app.Fragment;

public interface MainScreenActivityCallback {
    void manageFragments(Fragment fragment, String title);
    void setTitle(String title);
}
