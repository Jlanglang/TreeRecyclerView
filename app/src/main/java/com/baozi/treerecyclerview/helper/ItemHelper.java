package com.baozi.treerecyclerview.helper;

import com.baozi.treerecyclerview.base.BaseItemData;
import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/4/29.
 */

public class ItemHelper {
    /**
     * 不确定item的class类型
     *
     * @param list
     * @return
     */
    public static List<BaseItem> createItemList(List<? extends BaseItemData> list) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<BaseItem> baseItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BaseItemData itemData = list.get(i);
            try {
                Class<? extends BaseItem> itemClass = ItemConfig.getViewHolderType(itemData.getViewItemType());
                if (itemClass != null) {
                    BaseItem baseItem = itemClass.newInstance();
                    baseItem.setData(itemData);
                    baseItemList.add(baseItem);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baseItemList;
    }

    /**
     * 确定所有item的class类型
     *
     * @param list
     * @param iClass
     * @param <T>
     * @return
     */
    public static <T extends BaseItem> List<T> createItemList(List<? extends BaseItemData> list, Class<T> iClass) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<T> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BaseItemData itemData = list.get(i);
            try {
                Class<? extends BaseItem> itemClass = iClass == null
                        ? ItemConfig.getViewHolderType(itemData.getViewItemType()) : iClass;
                if (itemClass != null) {
                    T treeItem = (T) itemClass.newInstance();
                    treeItem.setData(itemData);
                    treeItemList.add(treeItem);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return treeItemList;
    }

    public static List<TreeItem> createTreeItemList(List<? extends BaseItemData> list, TreeItemGroup treeParentItem) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<TreeItem> baseItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BaseItemData itemData = list.get(i);
            try {
                Class<? extends BaseItem> itemClass = ItemConfig.getViewHolderType(itemData.getViewItemType());
                if (itemClass != null) {
                    TreeItem baseItem = (TreeItem) itemClass.newInstance();
                    baseItem.setData(itemData);
                    baseItem.setParentItem(treeParentItem);
                    baseItemList.add(baseItem);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baseItemList;
    }

    public static <T extends TreeItem> List<T> createTreeItemList(List<? extends BaseItemData> list, Class<T> iClass, TreeItemGroup treeParentItem) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<T> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BaseItemData itemData = list.get(i);
            try {
                Class<? extends BaseItem> itemClass = iClass == null
                        ? ItemConfig.getViewHolderType(itemData.getViewItemType()) : iClass;
                if (itemClass != null) {
                    T treeItem = (T) itemClass.newInstance();
                    treeItem.setData(itemData);
                    treeItem.setParentItem(treeParentItem);
                    treeItemList.add(treeItem);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return treeItemList;
    }

}
