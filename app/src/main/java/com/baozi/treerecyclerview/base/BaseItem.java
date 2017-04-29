package com.baozi.treerecyclerview.base;

import android.content.res.Resources;

import com.baozi.treerecyclerview.view.ViewHolder;

/**
 * 组合模式
 */
public abstract class BaseItem<D extends BaseItemData> {
    /**
     * 当前item的数据
     */
    protected D data;
    /**
     * item在每行中的spansize
     * 默认为0,如果为0则占满一行
     * 不建议连续的两级,都设置该数值
     *
     * @return 所占值
     */
    private int spanSize;

    public int getLayoutId() {
        if (initLayoutId() == 0) {
            throw new Resources.NotFoundException("请设置布局Id");
        }
        return initLayoutId();
    }

    /**
     * 该条目的布局id
     *
     * @return 布局id
     */
    protected abstract int initLayoutId();

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
     */
    public abstract void onBindViewHolder(ViewHolder viewHolder);


    /**
     * 当前条目的点击回调
     */
    public void onClick() {

    }
}
