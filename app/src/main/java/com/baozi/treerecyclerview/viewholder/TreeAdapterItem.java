package com.baozi.treerecyclerview.viewholder;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/7.
 */
public abstract class TreeAdapterItem<T> {
    protected T data;
    protected List<TreeAdapterItem> childs;
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
     * @return
     */
    public List<TreeAdapterItem> onExpand() {
        return childs;
    }

    /**
     * 收拢,收缩
     *
     * @return
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

    public int getSpansize() {
        return 0;
    }

    public boolean isParent() {
        return childs != null && childs.size() > 0;
    }

    protected abstract List<TreeAdapterItem> initChildsList(T data);

    public abstract int grade();

    public abstract void onBindViewHolder(ViewHolder holder);
}
