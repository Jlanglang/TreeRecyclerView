package com.baozi.demo.item.sort

import android.graphics.Rect
import android.support.v7.widget.RecyclerView

import com.baozi.demo.R
import com.baozi.demo.item.swipe.SwipeSortItem
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.item.TreeSortItem

import java.util.ArrayList

/**
 * Created by baozi on 2017/8/19.
 */

class SortGroupItem : TreeSortItem<Any>() {
    override fun initChild(data: Any): List<TreeItem<*>>? {
        val treeItems = ArrayList<TreeItem<*>>()
        for (i in 0..4) {
            val sortChildItem = SwipeSortItem()
            sortChildItem.data = i.toString() + ""
            treeItems.add(sortChildItem)
        }
        return treeItems
    }


    override fun getLayoutId(): Int {
        return R.layout.item_sort_group
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {
        if (isExpand()) {
            viewHolder.setImageResource(R.id.iv_right, R.drawable.ic_keyboard_arrow_down_black_24dp)
        } else {
            viewHolder.setImageResource(R.id.iv_right, R.drawable.ic_keyboard_arrow_right_black_24dp)
        }
        viewHolder.setText(R.id.tv_content, sortKey as String?)
    }

    override fun onClick(viewHolder: ViewHolder) {
        super.onClick(viewHolder)
        if (isExpand()) {
            viewHolder.setImageResource(R.id.iv_right, R.drawable.ic_keyboard_arrow_down_black_24dp)
        } else {
            viewHolder.setImageResource(R.id.iv_right, R.drawable.ic_keyboard_arrow_right_black_24dp)
        }
    }

    override fun getItemOffsets(outRect: Rect, layoutParams: RecyclerView.LayoutParams, position: Int) {
        super.getItemOffsets(outRect, layoutParams, position)
        outRect.top = 10
        if (position == 0) {
            outRect.top = 0
        }
    }
}
