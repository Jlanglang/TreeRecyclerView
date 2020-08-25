package com.baozi.treerecyclerview.item;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.manager.ItemManager;

/**
 * TreeRecyclerAdapter的item
 */
public abstract class TreeItem<D> {
    /**
     * 当前item的数据
     */
    protected D data;
    private TreeItemGroup parentItem;
    /**
     * item在每行中的spansize
     * 默认为0,如果为0则占满一行
     */
    private int spanSize;
    private ItemManager mItemManager;

    public void setParentItem(TreeItemGroup parentItem) {
        this.parentItem = parentItem;
    }

    /**
     * 获取当前item的父级
     *
     */
    @Nullable
    public TreeItemGroup getParentItem() {
        return parentItem;
    }

    /**
     * 应该在void onBindViewHolder(ViewHolder viewHolder)的地方使用.
     * 如果要使用,可能为null,请加判断.
     *
     */
    public ItemManager getItemManager() {
        return mItemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        mItemManager = itemManager;
    }

    /**
     * 该条目的布局id
     *
     * @return 布局id
     */
    public int getLayoutId() {
        return 0;
    }

    /**
     * @param maxSpan 总数
     */
    public int getSpanSize(int maxSpan) {
        return spanSize == 0 ? maxSpan : spanSize;
    }


    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    /**
     * 设置当前条目间隔
     *
     */
    public void getItemOffsets(@NonNull Rect outRect, RecyclerView.LayoutParams layoutParams, int position) {

    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    /**
     * 抽象holder的绑定
     */
    public abstract void onBindViewHolder(@NonNull ViewHolder viewHolder);

    /**
     * 当前条目的点击回调
     */
    public void onClick(ViewHolder viewHolder) {

    }
}
