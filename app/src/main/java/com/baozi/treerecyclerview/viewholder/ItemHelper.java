package com.baozi.treerecyclerview.viewholder;

import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeParentItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemHelper {

    private static HashMap<Integer, Class<? extends TreeItem>> viewHolderTypes;

    static {
        viewHolderTypes = new HashMap<Integer, Class<? extends TreeItem>>();
    }

    public static int getViewHolderTypesCount() {
        return viewHolderTypes.size();
    }

    public static Class<? extends TreeItem> getViewHolderType(int type) {
        return viewHolderTypes.get(type);
    }

    /**
     * 不确定item的class类型
     * @param list
     * @return
     */
    public static List<TreeItem> createItemListForType(List<? extends ItemData> list) {
        return createItemListForType(list, null);
    }

    public static List<TreeItem> createItemListForType(List<? extends ItemData> list, TreeParentItem treeParentItem) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<TreeItem> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ItemData itemData = list.get(i);
            try {
                Class<? extends TreeItem> itemClass = getViewHolderType(itemData.getViewItemType());
                if (itemClass != null) {
                    TreeItem treeItem = itemClass.newInstance();
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

    /**
     * 确定所有item的class类型
     * @param list
     * @param iClass
     * @param <T>
     * @return
     */
    public static <T extends TreeItem> List<T> createItemListForClass(List<? extends ItemData> list, Class<T> iClass) {
        return createItemListForClass(list, iClass, null);
    }

    public static <T extends TreeItem> List<T> createItemListForClass(List<? extends ItemData> list, Class<T> iClass, TreeParentItem treeParentItem) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<T> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ItemData itemData = list.get(i);
            try {
                Class<? extends TreeItem> itemClass = iClass == null
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

    public static void addHolderType(int type, Class<? extends TreeItem> clazz) {
        viewHolderTypes.put(type, clazz);
    }
}