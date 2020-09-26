package com.baozi.treerecyclerview.item

import android.graphics.Rect
import android.support.v7.widget.RecyclerView

import com.baozi.treerecyclerview.base.ViewHolder

/**
 * 简单样式的item
 */
class SimpleTreeItem @JvmOverloads constructor(val layout: Int = 0, spanSize: Int = 0) : TreeItem<Any>() {
    var itemClick: ((view: ViewHolder) -> Unit)? = null
    var itemBind: ((view: ViewHolder) -> Unit)? = null
    var treeOffset: Rect? = null

    init {
        this.spanSize = spanSize
    }

    override fun getLayoutId(): Int {
        return layout
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {
        itemBind?.invoke(viewHolder)
    }

    override fun onClick(viewHolder: ViewHolder) {
        itemClick?.invoke(viewHolder)
    }

    override fun getItemOffsets(outRect: Rect, layoutParams: RecyclerView.LayoutParams, position: Int) {
        treeOffset ?: return
        outRect.set(treeOffset)
    }

    override fun getSpanSize(maxSpan: Int): Int {
        return if (spanSize == 0) spanSize else maxSpan / spanSize
    }

    fun <T> getCastData(): T? {
        return data as? T
    }
}
