package com.baozi.demo.item.news

import android.graphics.Rect
import android.support.v7.widget.RecyclerView

import com.baozi.demo.R
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItem

/**
 * @author jlanglang  2017/7/5 17:15
 * @版本 2.0
 * @Change
 */

class NewsImageItem : TreeItem<String>() {
    override fun getLayoutId(): Int {
        return R.layout.item_news_image
    }

    override fun getSpanSize(maxSpan: Int): Int {
        return maxSpan / 3
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {

    }

    override fun getItemOffsets(outRect: Rect, layoutParams: RecyclerView.LayoutParams, position: Int) {
        super.getItemOffsets(outRect, layoutParams, position)
        outRect.set(1, 1, 1, 1)
    }
}
