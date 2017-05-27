package com.baozi.treerecyclerview.utils;

import android.support.annotation.NonNull;

import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/5/17.
 */

public class ItemHelper {
    @NonNull
    public static ArrayList<TreeItem> getChildItemsWithType(TreeItemGroup treeItemGroup, TreeRecyclerViewType type) {
        ArrayList<TreeItem> baseItems = new ArrayList<>();
        List allChild = treeItemGroup.getChilds();
        int childCount = treeItemGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            //下级
            TreeItem baseItem = (TreeItem) allChild.get(i);
            baseItems.add(baseItem);
            //判断下级是否为TreeItemGroup
            if (baseItem instanceof TreeItemGroup) {
                List list = null;
                switch (type) {
                    case SHOW_ALL:
                        //调用下级的getAllChilds遍历,相当于递归遍历
                        list = ((TreeItemGroup) baseItem).getAllChilds();
                        break;
                    case SHOW_EXPAND:
                        //根据isExpand,来决定是否展示
                        if (((TreeItemGroup) baseItem).isExpand()) {
                            list = ((TreeItemGroup) baseItem).getExpandChilds();
                        }
                        break;
                    case SHOW_DEFUTAL:
                        break;
                }
                if (list != null && list.size() > 0) {
                    baseItems.addAll(list);
                }
            }
        }
        return baseItems;
    }

    @NonNull
    public static ArrayList<TreeItem> getChildItemsWithType(List<TreeItem> treeItems, TreeRecyclerViewType type) {
        ArrayList<TreeItem> baseItems = new ArrayList<>();
        int childCount = treeItems.size();
        for (int i = 0; i < childCount; i++) {
            TreeItem treeItem = treeItems.get(i);
            baseItems.add(treeItem);
            if (treeItem instanceof TreeItemGroup) {
                ArrayList<TreeItem> childItems = getChildItemsWithType((TreeItemGroup) treeItem, type);
                if (!childItems.isEmpty()) {
                    baseItems.addAll(childItems);
                }
            }
        }
        return baseItems;
    }
}
