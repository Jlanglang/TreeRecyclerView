package com.baozi.treerecyclerview.manager;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;

import java.util.List;

/**
 * 条目增删管理类
 *
 * @param <T>
 */
public abstract class ItemManager<T> {

    private BaseRecyclerAdapter<T> mAdapter;

    public ItemManager(BaseRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
    }

    public BaseRecyclerAdapter<T> getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BaseRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
    }

    private boolean isOpenAnim = true;

    public boolean isOpenAnim() {
        return isOpenAnim;
    }

    public void setOpenAnim(boolean openAnim) {
        isOpenAnim = openAnim;
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

    public abstract void clean();

    //刷新
    public void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }

}