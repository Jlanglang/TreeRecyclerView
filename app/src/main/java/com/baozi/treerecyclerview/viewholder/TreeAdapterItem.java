package com.baozi.treerecyclerview.viewholder;

import android.content.res.ObbInfo;
import android.content.res.Resources;
import android.support.annotation.LayoutRes;

import com.baozi.treerecyclerview.adpater.TreeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 组合模式
 */
public abstract class TreeAdapterItem<D> {
    /**
     * 当前item的数据
     */
    protected D data;
    /**
     * 持有的子item
     */
    protected List<TreeAdapterItem> childs = new ArrayList<>();
    /**
     * 是否展开
     */
    protected boolean isExpand;
    /**
     * 布局资源id
     */
    protected int layoutId;
    /**
     * 每行所占比例
     */
    protected int spanSize;

    public TreeAdapterItem(D data) {
        this.data = data;
        List<TreeAdapterItem> treeAdapterItems = initChildsList(data);
        if (treeAdapterItems != null) {
            childs.addAll(treeAdapterItems);
        }
        layoutId = initLayoutId();
        spanSize = initSpansize();
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

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

//    /**
//     * 如果需要子类展开,重写该方法
//     */
//    public boolean isShowChild() {
//        return isShowChild;
//    }
//
//    public void setShowChild(boolean showChild) {
//        this.isShowChild = showChild;
//    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }


    public List<TreeAdapterItem<D>> getChilds() {
//        if (isShowChild()) {
        ArrayList<TreeAdapterItem<D>> treeAdapterItems = new ArrayList<>();
        for (int i = 0; i < childs.size(); i++) {
            TreeAdapterItem treeAdapterItem = childs.get(i);//下级
            treeAdapterItems.add(treeAdapterItem);//直接add
            if (treeAdapterItem.isExpand()) {//判断是否还有下下级
                List list = treeAdapterItem.getChilds();//调用下级的getAllChilds遍历,相当于递归遍历
                if (list != null && list.size() > 0) {
                    treeAdapterItems.addAll(list);
                }
            }
        }
        return treeAdapterItems;
    }

    /**
     * 递归遍历所有的子数据，包括子数据的子数据
     *
     * @return List<TreeAdapterItem>
     */
    public List<TreeAdapterItem> getAllChilds() {
        ArrayList<TreeAdapterItem> treeAdapterItems = new ArrayList<>();
        for (int i = 0; i < childs.size(); i++) {
            TreeAdapterItem treeAdapterItem = childs.get(i);//下级
            treeAdapterItems.add(treeAdapterItem);//
            if (treeAdapterItem.isParent()) {//判断是否还有下下级
//                treeAdapterItem.onCollapse();
                List list = treeAdapterItem.getAllChilds();//调用下级的getAllChilds遍历,相当于递归遍历
                if (list != null && list.size() > 0) {
                    treeAdapterItems.addAll(list);
                }
            }
        }
        return treeAdapterItems;
    }

    public void setChilds(List<TreeAdapterItem> childs) {
        this.childs = childs;
    }

    public void addChild(TreeAdapterItem treeAdapterItem) {
        childs.add(treeAdapterItem);
    }

    public void addChild(TreeAdapterItem treeAdapterItem, int position) {
        childs.add(treeAdapterItem);
    }

    public void removeChild(TreeAdapterItem treeAdapterItem) {
        childs.remove(treeAdapterItem);
    }

    public void removeChild(int position) {
        childs.remove(position);
    }

    public void cleanChild() {
        childs.clear();
    }

    /**
     * 展开
     */
    public void onExpand() {
        isExpand = true;
    }

    /**
     * 折叠
     */
    public void onCollapse() {
        isExpand = false;
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
    protected abstract List<TreeAdapterItem> initChildsList(D data);

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
}
