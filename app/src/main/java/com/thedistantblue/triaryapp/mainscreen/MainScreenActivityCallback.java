package com.thedistantblue.triaryapp.mainscreen;

public interface MainScreenActivityCallback {
    void switchFragment(TitledFragment fragment);
    void setTitle(int title);
}