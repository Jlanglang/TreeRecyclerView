package com.baozi.treerecyclerview.viewholder;

import android.content.res.Resources;
import android.support.annotation.LayoutRes;

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
    /**
     * 布局资源id
     */
    protected int layoutId;

    public TreeAdapterItem(T data) {
        this.data = data;
        childs = initChildsList(data);
        layoutId = initLayoutId();
    }

    public int getLayoutId() {
        if (layoutId == 0) {
            throw new Resources.NotFoundException("请设置布局Id");
        }
        return layoutId;
    }

    public void setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        if (!isParent()) {
            return;
        }
        if (expand) {
            onExpand();
        } else {
            onCollapse();
        }
        isExpand = expand;
    }

    /**
     * 展开
     */
    public void onExpand() {

    }

    /**
     * 折叠
     */
    public void onCollapse() {

    }

    public List<TreeAdapterItem> getChilds() {
        return childs;
    }

    public void setChilds(List<TreeAdapterItem> childs) {
        this.childs = childs;
    }

    /**
     * 递归遍历所有的子数据，包括子数据的子数据
     *
     * @return List<TreeAdapterItem>
     */
    public List<TreeAdapterItem> getAllChilds() {
        ArrayList<TreeAdapterItem> treeAdapterItems = new ArrayList<>();
        for (int i = 0; i < childs.size(); i++) {
            TreeAdapterItem treeAdapterItem = childs.get(i);
            treeAdapterItems.add(treeAdapterItem);
            if (treeAdapterItem.isParent()) {
                List list = treeAdapterItem.getAllChilds();
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

}
