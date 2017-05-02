package com.baozi.treerecyclerview.adpater;

import com.baozi.treerecyclerview.base.BaseItem;

import java.util.List;

public interface ItemManager<T extends BaseItem> {
    void addTreeItem(T item);

    void addTreeItem(List<T> items);

    void removeItem(T item);

    void removeItem(List<T> items);

    void notifyItemChanged(int position);

    void notifyItemInserted(int position);

    void notifyItemRemoved(int position);

    void notifyItemRangeChanged(int positionStart, int itemCount);

    void notifyItemRangeInserted(int positionStart, int itemCount);

    void notifyItemRangeRemoved(int positionStart, int itemCount);

    void notifyDataSetChanged();
}