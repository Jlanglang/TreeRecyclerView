package com.baozi.treerecyclerview.adpater.wrapper;

import com.baozi.treerecyclerview.adpater.wrapper.swipe.SwipeLayout;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public interface SwipeItem {

    int getSwipeLayoutId();

    SwipeLayout.DragEdge getDragEdge();
}
