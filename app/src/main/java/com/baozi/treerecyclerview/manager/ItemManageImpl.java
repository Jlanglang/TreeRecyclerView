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
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        int itemPosition = getItemPosition(item);
        getAdapter().notifyItemInserted(itemPosition);
    }

    @Override
    public void addItem(int position, T item) {
        getDatas().add(position, item);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        int itemPosition = getItemPosition(item);
        getAdapter().notifyItemInserted(itemPosition);
    }

    @Override
    public void addItems(List<T> items) {
        getDatas().addAll(items);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        getAdapter().notifyItemRangeInserted(getDatas().size(), items.size());
    }

    @Override
    public void addItems(int position, List<T> items) {
        getDatas().addAll(position, items);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        getAdapter().notifyItemRangeInserted(position, items.size());
    }

    @Override
    public void removeItem(T item) {
        getDatas().remove(item);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        getAdapter().notifyItemRemoved(getItemPosition(item));
    }

    @Override
    public void removeItem(int position) {
        getDatas().remove(position);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        getAdapter().notifyItemRemoved(position);
    }

    @Override
    public void removeItems(List<T> items) {
        getDatas().removeAll(items);
        notifyDataChanged();
    }

    @Override
    public void replaceItem(int position, T item) {
        getDatas().set(position, item);
        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        getAdapter().notifyItemChanged(position);
    }

    @Override
    public void replaceAllItem(List<T> items) {
        if (items == null) {
            return;
        }
        setDatas(items);

        if (!isOpenAnim()) {
            notifyDataChanged();
            return;
        }
        getAdapter().notifyItemRangeChanged(0, getDatas().size());
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

    @Override
    public void clean() {
        getAdapter().clear();
        notifyDataChanged();
    }
}