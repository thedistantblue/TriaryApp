package com.thedistantblue.triaryapp.mainscreen;

import androidx.fragment.app.Fragment;

public interface MainScreenActivity {
    void switchFragment(Fragment fragment);
    void setTitle(int title);
}