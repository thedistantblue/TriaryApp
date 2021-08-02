package com.thedistantblue.triaryapp.mainscreen;

public abstract class TitledFragment extends AutoDisposableFragment {
    public String getName() {
        return this.getClass().getSimpleName();
    }
    public abstract int getTitle();
}