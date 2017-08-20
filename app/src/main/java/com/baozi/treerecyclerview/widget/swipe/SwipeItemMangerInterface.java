package com.baozi.treerecyclerview.widget.swipe;

import java.util.List;

public interface SwipeItemMangerInterface {
    void bind(SwipeLayout swipeLayout, int res, int position);

    void openItem(int position);

    void closeItem(int position);

    void closeAllExcept(SwipeLayout layout);

    void closeAllItems();

    List<Integer> getOpenItems();

    List<SwipeLayout> getOpenLayouts();

    void removeShownLayouts(SwipeLayout layout);

    boolean isOpen(int position);

    SwipeMode getMode();

    void setMode(SwipeMode mode);
}
