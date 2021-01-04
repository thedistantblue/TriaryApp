package com.thedistantblue.triaryapp.mainscreen;

import androidx.fragment.app.Fragment;

public interface MainScreenActivityCallback {
    void manageFragments(Fragment fragment, int title);
    void setTitle(int title);
}
