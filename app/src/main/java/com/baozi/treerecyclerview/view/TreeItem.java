package com.baozi.treerecyclerview.view;

import android.support.annotation.Nullable;

import com.baozi.treerecyclerview.modle.ItemData;

/**
 * Created by baozi on 2017/4/26.
 */

public abstract class TreeItem<D extends ItemData> extends BaseItem<D> {
    private TreeItemGroup parentItem;
    private TreeItemManager mTreeItemManager;

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
    public TreeItemManager getTreeItemManager() {
        return mTreeItemManager;
    }

    public void setTreeItemManager(TreeItemManager treeItemManager) {
        mTreeItemManager = treeItemManager;
    }
}
