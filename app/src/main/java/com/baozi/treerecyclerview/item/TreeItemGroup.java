package com.baozi.treerecyclerview.item;

import android.support.annotation.Nullable;

import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;

import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 * //拥有子集
 * //子集可以是parent,也可以是child
 * //可展开折叠
 */

public abstract class TreeItemGroup<D> extends TreeItem<D> {

    /**
     * 持有的子item
     */
    private List<TreeItem> childs;

    /**
     * 是否展开
     */
    private boolean isExpand;

    public boolean isExpand() {
        return isExpand;
    }

    /**
     * 设置为传入
     *
     * @param expand 传入true则展开,传入false则折叠
     */
    public void setExpand(boolean expand) {
        if (isCanExpand()) {
            isExpand = expand;
        }
    }

    /**
     * 刷新Item的展开状态
     */
    public void notifyExpand() {
        if (isExpand()) {
            onExpand();
        } else {
            onCollapse();
        }
    }

    /**
     * 展开
     */
    public void onExpand() {
        isExpand = true;
        int itemPosition = getItemManager().getItemPosition(this);
        getItemManager().addItems(itemPosition + 1, getExpandChilds());
        getItemManager().notifyDataChanged();
    }

    /**
     * 折叠
     */
    public void onCollapse() {
        isExpand = false;
        getItemManager().removeItems(getExpandChilds());
        getItemManager().notifyDataChanged();
    }

    /**
     * 能否展开折叠
     *
     * @return
     */
    public boolean isCanExpand() {
        return true;
    }


    /**
     * 获得所有childs,包括子item的childs
     *
     * @return
     */
    @Nullable
    public List<TreeItem> getExpandChilds() {
        if (getChilds() == null) {
            return null;
        }
        return ItemHelperFactory.getChildItemsWithType(this, TreeRecyclerViewType.SHOW_EXPAND);
    }


    public void setData(D data) {
        super.setData(data);
        childs = initChildsList(data);
    }

    public void setChilds(List<TreeItem> childs) {
        this.childs = childs;
    }

    /**
     * 获得所有childs,包括下下....级item的childs
     *
     * @return
     */
    @Nullable
    public List<TreeItem> getAllChilds() {
        if (getChilds() == null) {
            return null;
        }
        return ItemHelperFactory.getChildItemsWithType(this, TreeRecyclerViewType.SHOW_ALL);
    }

    /**
     * 获得自己的childs.
     *
     * @return
     */
    @Nullable
    public List<TreeItem> getChilds() {
        return childs;
    }


    public int getChildCount() {
        return childs == null ? 0 : childs.size();
    }

    /**
     * 初始化子集
     *
     * @param data
     * @return
     */
    protected abstract List<TreeItem> initChildsList(D data);

    /**
     * 是否消费child的click事件
     *
     * @param child 具体click的item
     * @return 返回true代表消费此次事件，child不会走onclick()，返回false说明不消费此次事件，child依然会走onclick()
     */
    public boolean onInterceptClick(TreeItem child) {
        return false;
    }


}
