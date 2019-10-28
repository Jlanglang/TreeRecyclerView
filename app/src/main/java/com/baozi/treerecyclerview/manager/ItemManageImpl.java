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
        getData().add(item);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        int itemPosition = getItemPosition(item);
        getAdapter().notifyItemInserted(itemPosition);
    }

    @Override
    public void addItem(int position, T item) {
        getData().add(position, item);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        position = dataToItemPosition(position);
        getAdapter().notifyItemInserted(position);
    }

    @Override
    public void addItems(List<T> items) {
        getData().addAll(items);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        getAdapter().notifyItemRangeInserted(getData().size(), items.size());
    }

    @Override
    public void addItems(int position, List<T> items) {
        getData().addAll(position, items);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        int itemPosition = dataToItemPosition(position);
        getAdapter().notifyItemRangeInserted(itemPosition, items.size());
    }

    @Override
    public void removeItem(T item) {
        int position = getItemPosition(item);
        getData().remove(item);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        int itemPosition = dataToItemPosition(position);
        getAdapter().notifyItemRemoved(itemPosition);
    }

    @Override
    public void removeItem(int position) {
        getData().remove(position);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        int itemPosition = dataToItemPosition(position);
        getAdapter().notifyItemRemoved(itemPosition);
    }

    @Override
    public void removeItems(List<T> items) {
        getData().removeAll(items);
        notifyDataChanged();
    }

    @Override
    public void replaceItem(int position, T item) {
        getData().set(position, item);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        int itemPosition = dataToItemPosition(position);
        getAdapter().notifyItemChanged(itemPosition);
    }

    @Override
    public void replaceAllItem(List<T> items) {
        setData(items);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        getAdapter().notifyItemRangeChanged(0, getData().size());
    }

    protected void setData(List<T> items) {
        getAdapter().setData(items);
    }

    protected List<T> getData() {
        return getAdapter().getData();
    }

    @Override
    public T getItem(int position) {
        return getData().get(position);
    }

    @Override
    public int getItemPosition(T item) {
        return getData().indexOf(item);
    }

    @Override
    public void clean() {
        getAdapter().clear();
        notifyDataChanged();
    }
}