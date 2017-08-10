package com.baozi.treerecyclerview.item;

import com.baozi.treerecyclerview.widget.TreeSortAdapter;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public abstract class TreeSortItem<T> extends TreeItemGroup<T> {
    protected Object sortKey;

    public void setSortKey(Object sortKey) {
        this.sortKey = sortKey;
    }

    public Object getSortKey() {
        return sortKey;
    }

    @Override
    public void onCollapse() {
        super.onCollapse();
        getItemManager().updataSort(this);
    }

    @Override
    public void onExpand() {
        super.onExpand();
        getItemManager().updataSort(this);
    }

    @Override
    public TreeSortAdapter.TreeSortManageWapper getItemManager() {
        return (TreeSortAdapter.TreeSortManageWapper) super.getItemManager();
    }
}
