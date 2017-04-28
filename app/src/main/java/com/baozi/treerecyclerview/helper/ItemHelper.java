package com.baozi.treerecyclerview.helper;

import com.baozi.treerecyclerview.view.BaseItem;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;
import com.baozi.treerecyclerview.modle.ItemData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemHelper {

    private static HashMap<Integer, Class<? extends BaseItem>> viewHolderTypes;

    static {
        viewHolderTypes = new HashMap<>();
    }

    public static int getViewHolderTypesCount() {
        return viewHolderTypes.size();
    }

    public static Class<? extends BaseItem> getViewHolderType(int type) {
        return viewHolderTypes.get(type);
    }

    /**
     * 不确定item的class类型
     *
     * @param list
     * @return
     */
    public static List<BaseItem> createItemList(List<? extends ItemData> list) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<BaseItem> baseItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ItemData itemData = list.get(i);
            try {
                Class<? extends BaseItem> itemClass = getViewHolderType(itemData.getViewItemType());
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
    public static <T extends BaseItem> List<T> createItemList(List<? extends ItemData> list, Class<T> iClass) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<T> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ItemData itemData = list.get(i);
            try {
                Class<? extends BaseItem> itemClass = iClass == null
                        ? getViewHolderType(itemData.getViewItemType()) : iClass;
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

    public static List<TreeItem> createTreeItemList(List<? extends ItemData> list, TreeItemGroup treeParentItem) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<TreeItem> baseItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ItemData itemData = list.get(i);
            try {
                Class<? extends BaseItem> itemClass = getViewHolderType(itemData.getViewItemType());
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

    public static <T extends TreeItem> List<T> createTreeItemList(List<? extends ItemData> list, Class<T> iClass, TreeItemGroup treeParentItem) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<T> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ItemData itemData = list.get(i);
            try {
                Class<? extends BaseItem> itemClass = iClass == null
                        ? getViewHolderType(itemData.getViewItemType()) : iClass;
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

    public static void addHolderType(int type, Class<? extends BaseItem> clazz) {
        if (null == clazz) {
            return;
        }
        viewHolderTypes.put(type, clazz);
    }
}