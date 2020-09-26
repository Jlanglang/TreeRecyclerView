package com.baozi.treerecyclerview.item

import android.graphics.Rect
import android.support.v7.widget.RecyclerView

import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.manager.ItemManager

/**
 * TreeRecyclerAdapter的item
 */
abstract class TreeItem<D> {
    /**
     * 当前item的数据
     */
    open var data: D? = null
    /**
     * 获取当前item的父级
     *
     */
    var parentItem: TreeItemGroup<*>? = null
    /**
     * item在每行中的spansize
     * 默认为0,如果为0则占满一行
     */
    private var spanSize: Int = 0
    /**
     * 应该在void onBindViewHolder(ViewHolder viewHolder)的地方使用.
     * 如果要使用,可能为null,请加判断.
     *
     */
    var itemManager: ItemManager<TreeItem<*>>? = null

//    /**
//     * 该条目的布局id
//     *
//     * @return 布局id
//     */
//    open val layoutId: Int

    open fun getLayoutId(): Int {
        return 0;
    }

    /**
     * @param maxSpan 总数
     */
    open fun getSpanSize(maxSpan: Int): Int {
        return if (spanSize == 0) maxSpan else spanSize
    }


    fun setSpanSize(spanSize: Int) {
        this.spanSize = spanSize
    }

    /**
     * 设置当前条目间隔
     *
     */
    open fun getItemOffsets(outRect: Rect, layoutParams: RecyclerView.LayoutParams, position: Int) {

    }

    /**
     * 抽象holder的绑定
     */
    abstract fun onBindViewHolder(viewHolder: ViewHolder)

    /**
     * 当前条目的点击回调
     */
    open fun onClick(viewHolder: ViewHolder) {

    }
}