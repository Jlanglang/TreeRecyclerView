package com.baozi.treerecyclerview.view;

import android.content.res.Resources;

import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.viewholder.ItemData;

/**
 * 组合模式
 */
public abstract class TreeItem<D extends ItemData> {

    protected TreeParentItem parentItem;
    /**
     * 当前item的数据
     */
    protected D data;
    /**
     * 布局资源id
     */
    private int layoutId;
    /**
     * 每行所占比例
     */
    private int spanSize;


    private onClickChangeListener mOnClickChangeListener;

    public TreeItem() {

    }

    public TreeItem(TreeParentItem parentItem) {
        this.parentItem = parentItem;
    }

    public int getLayoutId() {
        layoutId = initLayoutId();
        if (layoutId == 0) {
            throw new Resources.NotFoundException("请设置布局Id");
        }
        return layoutId;
    }

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

    public void setParentItem(TreeParentItem parentItem) {
        this.parentItem = parentItem;
    }

    public onClickChangeListener getOnClickChangeListener() {
        return mOnClickChangeListener;
    }

    /**
     * item在每行中的spansize
     * 默认为0,如果为0则占满一行
     * 不建议连续的两级,都设置该数值
     *
     * @return 所占值
     */
    protected int initSpansize() {
        return 0;
    }

    /**
     * 该条目的布局id
     *
     * @return 布局id
     */
    protected abstract int initLayoutId();

    /**
     * 抽象holder的绑定
     *
     * @param holder ViewHolder
     */
    public abstract void onBindViewHolder(ViewHolder holder);

    /**
     * 当item被点击时
     */
    public void onClickChange(TreeItem treeItem) {
        if (mOnClickChangeListener != null) {
            mOnClickChangeListener.onClickChange(treeItem);
        }
    }


    public void setOnClickChangeListener(onClickChangeListener onClickChangeListener) {
        mOnClickChangeListener = onClickChangeListener;
    }

    /**
     * 获取当前item的父级
     *
     * @return
     */
    public TreeParentItem getParentItem() {
        return parentItem;
    }

    public interface onClickChangeListener {
        void onClickChange(TreeItem treeItem);
    }
}
