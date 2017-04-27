package com.baozi.treerecyclerview.view;

import android.content.res.Resources;

import com.baozi.treerecyclerview.modle.ItemData;

/**
 * 组合模式
 */
public abstract class BaseItem<D extends ItemData> {

    /**
     * 当前item的数据
     */
    protected D data;
    /**
     * 布局资源id
     */
    protected int layoutId;
    /**
     * item在每行中的spansize
     * 默认为0,如果为0则占满一行
     * 不建议连续的两级,都设置该数值
     *
     * @return 所占值
     */
    private int spanSize;


    public BaseItem() {

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


    /**
     * 该条目的布局id
     *
     * @return 布局id
     */
    protected abstract int initLayoutId();

    /**
     * 抽象holder的绑定
     */
    public abstract void onBindViewHolder(ViewHolder viewHolder);


    /**
     * 当前条目的点击回调
     *
     */
    public void onClick() {

    }
}
