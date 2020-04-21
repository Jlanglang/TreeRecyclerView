package com.baozi.treerecyclerview.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.base.ViewHolder;
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
    /**
     * 是否能展开
     */
    private boolean isCanExpand = true;

    /**
     * 能否展开折叠
     *
     * @return
     */
    public boolean isCanExpand() {
        return isCanExpand;
    }

    public void setCanExpand(boolean canExpand) {
        isCanExpand = canExpand;
    }

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
        if (expand == isExpand) {//防止重复展开
            return;
        }
        isExpand = expand;
        if (expand) {
            onExpand();
        } else {
            onCollapse();
        }
    }

    /**
     * 展开
     */
    protected void onExpand() {
        ItemManager itemManager = getItemManager();
        if (itemManager == null) {
            return;
        }
        List<TreeItem> child = this.getChild();
        if (child == null || child.size() == 0) {
            isExpand = false;
            return;
        }
        int itemPosition = itemManager.getItemPosition(this);
        itemManager.addItems(itemPosition + 1, child);
    }

    /**
     * 折叠
     */
    protected void onCollapse() {
        ItemManager itemManager = getItemManager();
        if (itemManager == null) {
            return;
        }
        List<TreeItem> child = this.getChild();
        if (child == null || child.size() == 0) {
            isExpand = false;
            return;
        }
        itemManager.removeItems(child);
    }


    /**
     * 获得所有展开的childs,包括子item的childs
     *
     * @return
     */
    @NonNull
    public List<TreeItem> getExpandChild() {
        return ItemHelperFactory.getChildItemsWithType(this, TreeRecyclerType.SHOW_EXPAND);
    }


    public void setData(D data) {
        super.setData(data);
        child = initChild(data);
    }

    /**
     * 获得所有childs,包括下下....级item的childs
     *
     * @return
     */
    @Nullable
    public List<TreeItem> getAllChilds() {
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
    protected abstract List<TreeItem> initChild(D data);

    /**
     * 是否消费child的click事件
     *
     * @param child 具体click的item
     * @return 返回true代表消费此次事件，child不会走onclick()，返回false说明不消费此次事件，child依然会走onclick()
     */
    public boolean onInterceptClick(TreeItem child) {
        return false;
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        super.onClick(viewHolder);
        //必须是TreeItemGroup才能展开折叠,并且type不能为 TreeRecyclerType.SHOW_ALL
        setExpand(!isExpand());
    }
}
