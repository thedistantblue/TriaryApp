package com.thedistantblue.triaryapp.mainscreen.utils.recycler.touch;

import android.content.Context;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

    void onRefresh(int position);

    Context getContext();
}
