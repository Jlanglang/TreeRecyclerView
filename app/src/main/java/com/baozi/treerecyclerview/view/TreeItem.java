package com.baozi.treerecyclerview.view;

import android.support.annotation.Nullable;

/**
 * Created by baozi on 2017/4/26.
 */

public abstract class TreeItem<D extends ItemData> extends BaseItem<D> {

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
}
