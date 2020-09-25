package com.baozi.demo.item.mine

import android.graphics.Rect
import android.support.v7.widget.RecyclerView

import com.baozi.demo.R
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItem

class MineHeadItem : TreeItem<String>() {
    override fun getLayoutId(): Int {
        return R.layout.item_mine_head
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {}

    override fun getItemOffsets(outRect: Rect, layoutParams: RecyclerView.LayoutParams, position: Int) {
        super.getItemOffsets(outRect, layoutParams, position)
        outRect.bottom = 1
    }
}
