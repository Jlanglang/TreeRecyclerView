package com.baozi.treerecyclerview.item;

import android.content.res.Resources;
import android.support.annotation.Nullable;

import com.baozi.treerecyclerview.manager.ItemManager;
import com.baozi.treerecyclerview.base.ViewHolder;

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
     *
     * @return 所占值, 比如recyclerview的列数为6, item需要占一半宽度, 就设置3
     */
    private int spanSize;
    private ItemManager mItemManager;

    public void setParentItem(TreeItemGroup parentItem) {
        this.parentItem = parentItem;
    }

    /**
     * 获取当前item的父级
     *
     * @return
     */
    @Nullable
    public TreeItemGroup getParentItem() {
        return parentItem;
    }

    /**
     * 应该在void onBindViewHolder(ViewHolder viewHolder)的地方使用.
     * 如果要使用,可能为null,请加判断.
     *
     * @return
     */
    public ItemManager getItemManager() {
        return mItemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        mItemManager = itemManager;
    }

    public int getLayoutId() {
        if (initLayoutId() <= 0) {
            throw new Resources.NotFoundException("请设置布局Id");
        }
        return initLayoutId();
    }


    /**
     * 该条目的布局id
     *
     * @return 布局id
     */
    protected abstract int initLayoutId();

    /**
     * 觉得item的所占比例
     *
     * @return , 如果设置的列数为6, 返回3, 则代表item占1半宽度
     */
    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
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
    public abstract void onBindViewHolder(ViewHolder viewHolder);

    /**
     * 当前条目的点击回调
     */
    public void onClick(ViewHolder viewHolder) {

    }

}
