package com.baozi.treerecyclerview.view;

import com.baozi.treerecyclerview.adpater.ItemManager;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.base.BaseItem;

/**
 * Created by baozi on 2017/6/22.
 */

public class TreeItemWapper extends TreeItem {
    private BaseItem mBaseItem;

    public TreeItemWapper(BaseItem baseItem) {
        mBaseItem = baseItem;
        setSpanSize(mBaseItem.getSpanSize());
        setItemManager(mBaseItem.getItemManager());
        setData(mBaseItem.getData());
    }

    @Override
    protected int initLayoutId() {
        return mBaseItem.getLayoutId();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        mBaseItem.onBindViewHolder(viewHolder);
    }
}
