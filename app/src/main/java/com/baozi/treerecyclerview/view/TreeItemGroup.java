package com.baozi.treerecyclerview.view;

import android.support.annotation.Nullable;

import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;
import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.base.BaseItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 * //拥有子集
 * //子集可以是parent,也可以是child
 */

public abstract class TreeItemGroup<D extends BaseItemData> extends TreeItem<D>
        implements TreeParent {
    /**
     * 持有的子item
     */
    private List<? extends BaseItem> childs;
    /**
     * 是否展开
     */
    private boolean isExpand;
    /**
     * 能否展开
     */
    protected boolean isCanChangeExpand = true;

    public TreeItemGroup() {

    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        if (isCanChangeExpand()) {
            isExpand = expand;
        }
    }

    public void setData(D data) {
        super.setData(data);
        childs = initChildsList(data);
    }

    /**
     * 展开
     */
    @Override
    public void onExpand() {

    }

    /**
     * 折叠
     */
    @Override
    public void onCollapse() {

    }

    public boolean isCanChangeExpand() {
        return isCanChangeExpand;
    }
    @Nullable
    public List<? extends BaseItem> getChilds() {
        return childs;
    }

    public List<? extends BaseItem> getAllChilds(TreeRecyclerViewType treeRecyclerViewType) {
        ArrayList<BaseItem> baseItems = new ArrayList<>();
        for (int i = 0; i < childs.size(); i++) {
            //下级
            BaseItem baseItem = childs.get(i);
            baseItems.add(baseItem);
            //判断是否还有下下级,并且处于expand的状态
            if (baseItem instanceof TreeItemGroup && ((TreeItemGroup) baseItem).isExpand()) {
                //调用下级的getAllChilds遍历,相当于递归遍历
                List list = ((TreeItemGroup) baseItem).getAllChilds(treeRecyclerViewType);
                if (list != null && list.size() > 0) {
                    baseItems.addAll(list);
                }
            }
        }
        return baseItems;
    }

    public int getChildsCount() {
        return childs == null ? 0 : childs.size();
    }

    public <T extends BaseItem> T getChildsAt(int index) {
        if (index < 0 || index > getChildsCount()) {
            return null;
        }
        return (T) childs.get(index);
    }

    /**
     * 初始化子集
     *
     * @param data
     * @return
     */
    protected abstract List<? extends BaseItem> initChildsList(D data);

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
