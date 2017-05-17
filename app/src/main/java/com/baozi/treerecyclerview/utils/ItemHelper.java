package com.baozi.treerecyclerview.utils;

import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/5/17.
 */

public class ItemHelper {

    public static <T extends TreeItem> void setItemExpand(List<TreeItem> items, Class<T> clsss, boolean expand) {
        if (items == null) {
            return;
        }
        int size = items.size();
        ArrayList<TreeItem> copyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeItem treeItem = items.get(i);
            copyList.add(treeItem);
            if (treeItem instanceof TreeItemGroup) {
                List allChilds = ((TreeItemGroup) treeItem).getAllChilds();
                if (allChilds != null) {
                    copyList.addAll(allChilds);
                }
            }
        }
        int size1 = copyList.size();
        for (int i = 0; i < size1; i++) {
            TreeItem treeItem = copyList.get(i);
            if (treeItem instanceof TreeItemGroup && treeItem.getClass().equals(clsss)) {
                ((TreeItemGroup) treeItem).setExpand(expand);
            }
        }
    }
}
