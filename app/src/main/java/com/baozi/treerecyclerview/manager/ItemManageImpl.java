package com.baozi.treerecyclerview.manager;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;

import java.util.List;

/**
 * 默认使用 notifyDataChanged();刷新.
 * 如果使用带动画效果的,条目过多可能会出现卡顿.
 */
public class ItemManageImpl<T> extends ItemManager<T> {

    public ItemManageImpl(BaseRecyclerAdapter<T> adapter) {
        super(adapter);
    }

    @Override
    public void addItem(T item) {
        getDatas().add(item);
        notifyDataChanged();
    }

    @Override
    public void addItem(int position, T item) {
        getDatas().add(position, item);
        notifyDataChanged();
    }

    @Override
    public void addItems(List<T> items) {
        getDatas().addAll(items);
        notifyDataChanged();
    }

    @Override
    public void addItems(int position, List<T> items) {
        getDatas().addAll(position, items);
        notifyDataChanged();
    }

    @Override
    public void removeItem(T item) {
        getDatas().remove(item);
        notifyDataChanged();
    }

    @Override
    public void removeItem(int position) {
        getDatas().remove(position);
        notifyDataChanged();
    }

    @Override
    public void removeItems(List<T> items) {
        getDatas().removeAll(items);
        notifyDataChanged();
    }

    @Override
    public void replaceItem(int position, T item) {
        getDatas().set(position, item);
        notifyDataChanged();
    }

    @Override
    public void replaceAllItem(List<T> items) {
        if (items != null) {
            setDatas(items);
            notifyDataChanged();
        }
    }

    protected void setDatas(List<T> items) {
        getAdapter().setDatas(items);
    }

    protected List<T> getDatas() {
        return getAdapter().getDatas();
    }

    @Override
    public T getItem(int position) {
        return getDatas().get(position);
    }

    @Override
    public int getItemPosition(T item) {
        return getDatas().indexOf(item);
    }
}