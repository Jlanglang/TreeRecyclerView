package com.baozi.treerecyclerview.viewholder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 * //拥有子集
 * //子集可以是parent,也可以是child
 * //可以展开与折叠
 * //持有adapter,因为可以影响子集
 */

public abstract class TreeParentItem<D> extends TreeItem implements ParentItem {

    /**
     * 持有的子item
     */
    protected List<TreeItem> childs = new ArrayList<>();
    /**
     * 是否展开
     */
    protected boolean isExpand;

    public TreeParentItem(D data) {
        super(data);
        List<TreeItem> treeItems = initChildsList(data);
        if (treeItems != null) {
            childs.addAll(treeItems);
        }
    }

    @Override
    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    /**
     * 展开
     */
    @Override
    public void onExpand() {
        isExpand = true;
    }

    /**
     * 折叠
     */
    @Override
    public void onCollapse() {
        isExpand = false;
    }

    @Override
    public List<TreeItem> getChilds() {
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        for (int i = 0; i < childs.size(); i++) {
            TreeItem treeItem = childs.get(i);//下级
            treeItems.add(treeItem);//直接add
            if (treeItem instanceof TreeParentItem) {//判断是否还有下下级
                List list = ((TreeParentItem) treeItem).getChilds();//调用下级的getAllChilds遍历,相当于递归遍历
                if (list != null && list.size() > 0) {
                    treeItems.addAll(list);
                }
            }
        }
        return treeItems;
    }

    /**
     * 递归遍历所有的子数据，包括子数据的子数据
     *
     * @return List<TreeItem>
     */
    @Override
    public List<TreeItem> getAllChilds() {
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        for (int i = 0; i < childs.size(); i++) {
            TreeItem treeItem = childs.get(i);//下级
            treeItems.add(treeItem);//
            if (treeItem instanceof TreeParentItem) {//判断是否还有下下级
                List list = ((TreeParentItem) treeItem).getAllChilds();//调用下级的getAllChilds遍历,相当于递归遍历
                if (list != null && list.size() > 0) {
                    treeItems.addAll(list);
                }
            }
        }
        return treeItems;
    }

    public void setChilds(List<TreeItem> childs) {
        this.childs = childs;
    }

    public void addChild(TreeItem treeItem) {
        childs.add(treeItem);
    }

    public void addChild(TreeItem treeItem, int position) {
        childs.add(treeItem);
    }

    public void removeChild(TreeItem treeItem) {
        childs.remove(treeItem);
    }

    public void removeChild(int position) {
        childs.remove(position);
    }

    public void cleanChild() {
        childs.clear();
    }

    /**
     * 初始化子数据
     *
     * @param data
     * @return
     */
    public abstract List<TreeItem> initChildsList(D data);
}
