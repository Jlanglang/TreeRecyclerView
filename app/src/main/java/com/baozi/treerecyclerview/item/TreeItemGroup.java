package com.baozi.treerecyclerview.item;

import android.support.annotation.Nullable;

import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.manager.ItemManager;

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
    private List<TreeItem> child;

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
    public final void setExpand(boolean expand) {
        if (!isCanExpand()) {
            return;
        }
        if (expand) {
            onExpand();
        } else {
            onCollapse();
        }
    }

    /**
     * 刷新Item的展开状态
     */
    @Deprecated
    public void notifyExpand() {
        setExpand(isExpand);
    }

    /**
     * 展开
     */
    protected void onExpand() {
        isExpand = true;
        ItemManager itemManager = getItemManager();
        if (itemManager != null) {
            int itemPosition = itemManager.getItemPosition(this);
            itemManager.addItems(itemPosition + 1, getExpandChild());
            itemManager.notifyDataChanged();
        }
    }

    /**
     * 折叠
     */
    protected void onCollapse() {
        isExpand = false;
        ItemManager itemManager = getItemManager();
        if (itemManager != null) {
            itemManager.removeItems(getExpandChild());
            itemManager.notifyDataChanged();
        }
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
    public List<TreeItem> getExpandChild() {
        if (getChild() == null) {
            return null;
        }
        return ItemHelperFactory.getChildItemsWithType(this, TreeRecyclerType.SHOW_EXPAND);
    }


    public void setData(D data) {
        super.setData(data);
        child = initChildList(data);
    }

    public void setChild(List<TreeItem> child) {
        this.child = child;
    }

    /**
     * 获得所有childs,包括下下....级item的childs
     *
     * @return
     */
    @Nullable
    public List<TreeItem> getAllChilds() {
        if (getChild() == null) {
            return null;
        }
        return ItemHelperFactory.getChildItemsWithType(this, TreeRecyclerType.SHOW_ALL);
    }

    /**
     * 获得自己的childs.
     *
     * @return
     */
    @Nullable
    public List<TreeItem> getChild() {
        return child;
    }


    public int getChildCount() {
        return child == null ? 0 : child.size();
    }

    /**
     * 初始化子集
     *
     * @param data
     * @return
     */
    @Nullable
    protected abstract List<TreeItem> initChildList(D data);

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
