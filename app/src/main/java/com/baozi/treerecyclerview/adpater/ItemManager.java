package com.baozi.treerecyclerview.adpater;

import android.support.v7.widget.RecyclerView;

import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.base.BaseItemData;
import com.baozi.treerecyclerview.view.TreeItemGroup;

import java.util.List;

public abstract class ItemManager<T extends BaseItem> {

    private BaseRecyclerAdapter<T> mAdapter;

    public ItemManager(BaseRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BaseRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
    }

    //增
    public abstract void addItem(T item);

    public abstract void addItem(int position, T item);

    public abstract void addItems(List<T> items);

    public abstract void addItems(int position, List<T> items);

    //删
    public abstract void removeItem(T item);

    public abstract void removeItem(int position);

    public abstract void removeItems(List<T> items);


    //改
    public abstract void replaceItem(int position, T item);

    public abstract void replaceAllItem(List<T> items);

    //查
    public abstract T getItem(int position);

    public abstract int getItemPosition(T item);

    //刷新
    public void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }
}