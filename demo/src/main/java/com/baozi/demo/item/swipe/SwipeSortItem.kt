package com.baozi.demo.item.swipe

import android.view.View
import android.widget.Toast

import com.baozi.demo.R
import com.baozi.demo.item.sort.SortChildItem
import com.baozi.treerecyclerview.item.SwipeItem
import com.baozi.treerecyclerview.widget.swipe.SwipeItemMangerInterface
import com.baozi.treerecyclerview.widget.swipe.SwipeLayout
import com.baozi.treerecyclerview.base.ViewHolder

/**
 * Created by baozi on 2017/8/20.
 */

class SwipeSortItem : SortChildItem(), SwipeItem {
    override val swipeLayoutId: Int
        get() = R.layout.layout_delete

    override val dragEdge: SwipeLayout.DragEdge
        get() = SwipeLayout.DragEdge.Right

    override fun onBindSwipeView(viewHolder: ViewHolder, position: Int, swipeManger: SwipeItemMangerInterface) {
        viewHolder.setOnClickListener(R.id.tv_delete) { v ->
            Toast.makeText(v.context, "删除", Toast.LENGTH_SHORT).show()
            swipeManger.closeAllItems()
        }
    }

    override fun openCallback() {

    }

    override fun onClick(viewHolder: ViewHolder) {
        super.onClick(viewHolder)
    }
}
