package com.baozi.treerecyclerview.view;

import android.support.annotation.Nullable;

import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.base.BaseItemData;

/**
 * TreeRecyclerAdapter的item
 */
public abstract class TreeItem<D> extends BaseItem<D> {
    private TreeItemGroup parentItem;

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

    public String getItemName() {
        return "TreeItem";
    }
}
