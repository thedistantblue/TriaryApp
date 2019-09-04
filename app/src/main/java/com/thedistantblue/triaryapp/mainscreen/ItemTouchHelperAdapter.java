package com.thedistantblue.triaryapp.mainscreen;

import android.content.Context;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

    void onRefresh(int position);

    Context getContext();
}
