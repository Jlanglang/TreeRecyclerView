package com.baozi.treerecyclerview.manager;


import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 条目增删管理类
 *
 * @param <T>
 */
public abstract class ItemManager<T> {

    private BaseRecyclerAdapter<T> mAdapter;
    private Object tag;

    public ItemManager(BaseRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
    }

    public BaseRecyclerAdapter<T> getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BaseRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
    }

    private boolean isOpenAnim;

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

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public Object getTag() {
        return this.tag;
    }

    /**
     * 检查item属性
     */
    public interface CheckItemInterface {
        int itemToDataPosition(int position);

        int dataToItemPosition(int index);
    }

    public void removeCheckItemInterfaces(CheckItemInterface itemInterface) {
        if (checkItemInterfaces == null) return;
        checkItemInterfaces.remove(itemInterface);
    }

    public void addCheckItemInterfaces(CheckItemInterface itemInterface) {
        if (checkItemInterfaces == null) {
            checkItemInterfaces = new ArrayList<>();
        }
        checkItemInterfaces.add(itemInterface);
    }

    private ArrayList<CheckItemInterface> checkItemInterfaces;

    /**
     * 解决holder角标与data的角标不一致问题。
     *
     * @param position
     * @return
     */
    public int itemToDataPosition(int position) {
        if (checkItemInterfaces != null) {
            for (CheckItemInterface itemInterface : checkItemInterfaces) {
                position = itemInterface.itemToDataPosition(position);
            }
        }
        return position;
    }

    /**
     * 解决data的角标与holder角标不一致问题。
     *
     * @param index
     * @return
     */
    public int dataToItemPosition(int index) {
        if (checkItemInterfaces != null) {
            for (CheckItemInterface itemInterface : checkItemInterfaces) {
                index = itemInterface.dataToItemPosition(index);
            }
        }
        return index;
    }
}