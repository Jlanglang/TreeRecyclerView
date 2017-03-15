package com.baozi.treerecyclerview.view;

import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 * //拥有子集
 * //子集可以是parent,也可以是child
 * //可以展开与折叠
 * //持有adapter,因为可以影响子集
 */

public abstract class TreeParentItem<D> extends TreeItem<D>
        implements ParentItem, TreeItemManager {
    /**
     * 持有的子item
     */
    private List<TreeItem> childs;

    /**
     * 是否展开
     */
    private boolean isExpand;

    public TreeParentItem(D data) {
        super(data);
        initChildsList(data);
    }

    public void setExpand(boolean expand) {
        if (canExpandOrCollapse()) {
            isExpand = expand;
        }
    }

    public abstract boolean canExpandOrCollapse();


    public boolean isExpand() {
        if (canExpandOrCollapse()) {
            return isExpand;
        } else {
            return true;
        }
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

    @Override
    public List<TreeItem> getChilds() {
        return childs = childs == null ? new ArrayList<TreeItem>() : childs;
    }

    public List<TreeItem> getChilds(TreeRecyclerViewType treeRecyclerViewType) {
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        for (int i = 0; i < childs.size(); i++) {
            TreeItem treeItem = childs.get(i);//下级
            treeItems.add(treeItem);//直接add
            if (treeItem instanceof TreeParentItem) {//判断是否还有下下级,并且处于expand的状态
                List list = ((TreeParentItem) treeItem).getChilds();//调用下级的getAllChilds遍历,相当于递归遍历
                if (list != null && list.size() > 0) {
                    treeItems.addAll(list);
                }
                if (treeRecyclerViewType == TreeRecyclerViewType.SHOW_COLLAPSE_CHILDS) {
                    ((TreeParentItem) treeItem).setExpand(false);
                    ((TreeParentItem) treeItem).onCollapse();
                }
            }
        }
        return treeItems;
    }

    /**
     * 初始化子数据
     *
     * @param data 父级数据
     * @return 得到处理好的子集
     */
    protected abstract void initChildsList(D data);


    /**
     * 添加view
     */
    public void addView(TreeItem treeItem) {
        if (null == childs) {
            childs = new ArrayList<>();
        }
        if (treeItem != null) {
            childs.add(treeItem);
            treeItem.parentItem = this;
        }
    }

    @Override
    public void updateView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void removeView(TreeItem view) {
        childs.remove(view);
    }
}
