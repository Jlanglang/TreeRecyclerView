package com.baozi.treerecyclerview.viewholder;

import com.baozi.treerecyclerview.adpater.TreeRecyclerViewAdapter;
import com.baozi.treerecyclerview.adpater.ViewHolder;

/**
 * 组合模式
 */
public abstract class TreeItem<D> {

    protected TreeParentItem parentItem;
    /**
     * 当前item的数据
     */
    protected D data;
    /**
     * 每行所占比例
     */
    private int spanSize;

    TreeRecyclerViewAdapter adapter;

    public TreeItem(D data) {
        this.data = data;
    }

    public void onAttchApater(TreeRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    public abstract int getLayoutId();

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }


    /**
     * 抽象holder的绑定
     *
     * @param holder ViewHolder
     */
    public abstract void onBindViewHolder(ViewHolder holder);


    /**
     * 获取当前item的父级
     *
     * @return
     */
    public TreeParentItem getParentItem() {
        return parentItem;
    }

    public void onClick() {

    }

}
