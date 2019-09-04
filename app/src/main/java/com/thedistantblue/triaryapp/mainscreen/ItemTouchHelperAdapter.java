package com.thedistantblue.triaryapp.mainscreen;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

    void onRefresh(int position);
}
