package com.yg.demoproject.view;

public interface ItemTouchHelperListener {
    void onItemSwiped(int position);
    void onItemMoved(int fromPosition, int toPosition);
}
