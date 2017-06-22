package com.baozi.treerecyclerview.view;

import android.support.annotation.Nullable;

import com.baozi.treerecyclerview.adpater.ItemManager;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.utils.ItemHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/6/21.
 */
public class HeadAndFootGroupWapper<D> extends TreeItemGroup<D> {

    private TreeItemGroup<D> mTreeItemGroup;
    private List<TreeItem> headItems = new ArrayList<>();
    private List<TreeItem> footItems = new ArrayList<>();

    public HeadAndFootGroupWapper(TreeItemGroup<D> treeItemGroup) {
        mTreeItemGroup = treeItemGroup;
    }

    @Override
    public void setExpand(boolean expand) {
        mTreeItemGroup.setExpand(expand);
    }

    @Override
    public boolean isExpand() {
        return mTreeItemGroup.isExpand();
    }

    @Override
    protected List<TreeItem> initChildsList(D data) {
        return mTreeItemGroup.initChildsList(data);
    }
    @Nullable
    @Override
    public List<TreeItem> getAllChilds() {
        List<TreeItem> allChilds = mTreeItemGroup.getAllChilds();
//        if (allChilds != null) {
//            allChilds.addAll(0, headItems);
//            allChilds.addAll(footItems);
//        }
        return allChilds;
    }

    @Override
    public void onCollapse() {
        mTreeItemGroup.onCollapse();
    }

    @Override
    public void onExpand() {
        mTreeItemGroup.onExpand();
    }

    @Nullable
    @Override
    public List<TreeItem> getExpandChilds() {
        List<TreeItem> expandChilds = mTreeItemGroup.getExpandChilds();

        return expandChilds;
    }

    @Override
    public int getChildCount() {
        return mTreeItemGroup.getChildCount();
    }

    @Override
    public int getSpanSize() {
        return mTreeItemGroup.getSpanSize();
    }

    @Override
    public ItemManager getItemManager() {
        return mTreeItemGroup.getItemManager();
    }

    @Override
    public int getLayoutId() {
        return mTreeItemGroup.getLayoutId();
    }

    @Override
    public D getData() {
        return mTreeItemGroup.getData();
    }

    @Override
    public void setChilds(List<TreeItem> childs) {
        mTreeItemGroup.setChilds(childs);
    }

    @Override
    public void setData(D data) {
        mTreeItemGroup.setData(data);
    }

    @Override
    public void setParentItem(TreeItemGroup parentItem) {
        mTreeItemGroup.setParentItem(parentItem);
    }

    @Override
    public boolean isCanExpand() {
        return mTreeItemGroup.isCanExpand();
    }

    @Nullable
    @Override
    public List<TreeItem> getChilds() {
        List<TreeItem> childs = mTreeItemGroup.getChilds();
        if (childs != null) {
            childs.addAll(0, headItems);
            childs.addAll(footItems);
        }
        return childs;
    }

    @Override
    protected int initLayoutId() {
        return mTreeItemGroup.getLayoutId();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        mTreeItemGroup.onBindViewHolder(viewHolder);
    }

    public void addHeadItem(TreeItem item) {
        headItems.add(item);
    }

    public void addfootItem(TreeItem item) {
        footItems.add(item);
    }
}
