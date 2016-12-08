package com.baozi.treerecyclerview.viewholder;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/7.
 */
public abstract class TreeAdapterItem<T> {
    /**
     * 当前item的数据
     */
    protected T data;
    /**
     * 持有的子数据
     */
    protected List<TreeAdapterItem> childs;
    /**
     * 是否展开
     */
    protected boolean isExpand;

    public TreeAdapterItem(T data) {
        this.data = data;
        childs = initChildsList(data);
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public List<TreeAdapterItem> getChilds() {
        return childs;
    }

    public void setChilds(List<TreeAdapterItem> childs) {
        this.childs = childs;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 展开
     *
     * @return 子数据集
     */
    public List<TreeAdapterItem> onExpand() {
        return childs;
    }

    /**
     * 收拢,收缩
     *
     * @return 所有的子数据, 包括孩子的子数据
     */
    public List<TreeAdapterItem> onGathered() {
        ArrayList<TreeAdapterItem> treeAdapterItems = new ArrayList<>();
        for (int i = 0; i < childs.size(); i++) {
            TreeAdapterItem treeAdapterItem = childs.get(i);
            treeAdapterItems.add(treeAdapterItem);
            if (treeAdapterItem.isParent()) {
                List list = treeAdapterItem.onGathered();
                if (list != null && list.size() > 0) {
                    treeAdapterItems.addAll(list);
                }
            }
        }
        return treeAdapterItems;
    }

    /**
     * item在每行中的spansize
     * 默认为0,如果为0则占满一行
     * 不建议连续的两级,都设置该数值
     *
     * @return 所占值
     */
    public int getSpansize() {
        return 0;
    }

    /**
     * 是否持有子数据
     *
     * @return
     */
    public boolean isParent() {
        return childs != null && childs.size() > 0;
    }

    /**
     * 初始化子数据
     *
     * @param data
     * @return
     */
    protected abstract List<TreeAdapterItem> initChildsList(T data);

    /**
     * @return 第几级
     */
    public abstract int grade();

    /**
     * 由实现类实现具体holder的设置.
     *
     * @param holder ViewHolder
     */
    public abstract void onBindViewHolder(ViewHolder holder);
}
