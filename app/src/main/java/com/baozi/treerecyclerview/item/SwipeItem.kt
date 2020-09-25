package com.baozi.treerecyclerview.item

import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.widget.swipe.SwipeItemMangerInterface
import com.baozi.treerecyclerview.widget.swipe.SwipeLayout

/**
 * Created by Administrator on 2017/8/18 0018.
 * 实现该接口,侧滑删除
 */

interface SwipeItem {

    val swipeLayoutId: Int

    val dragEdge: SwipeLayout.DragEdge

    fun onBindSwipeView(viewHolder: ViewHolder, position: Int, swipeManger: SwipeItemMangerInterface)

    fun openCallback()
}
