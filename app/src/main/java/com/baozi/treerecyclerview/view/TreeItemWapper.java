package com.baozi.treerecyclerview.view;

import android.support.annotation.Nullable;

import com.baozi.treerecyclerview.adpater.ItemManager;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.base.BaseItem;

/**
 * Created by baozi on 2017/6/22.
 * 将BaseItem转换为TreeItem
 */

public class TreeItemWapper extends TreeItem {

    private BaseItem mBaseItem;

    public TreeItemWapper(BaseItem baseItem) {
        mBaseItem = baseItem;
    }

    @Override
    public void setSpanSize(int spanSize) {
        mBaseItem.setSpanSize(spanSize);
    }

    @Override
    public int getSpanSize() {
        return mBaseItem.getSpanSize();
    }

    @Override
    public void setData(Object data) {
        mBaseItem.setData(data);
    }

    @Override
    public Object getData() {
        return mBaseItem.getData();
    }

    @Override
    public void setItemManager(ItemManager itemManager) {
        mBaseItem.setItemManager(itemManager);
    }

    @Override
    public ItemManager getItemManager() {
        return mBaseItem.getItemManager();
    }

    @Override
    public int getLayoutId() {
        return mBaseItem.getLayoutId();
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
