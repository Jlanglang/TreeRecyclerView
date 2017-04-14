package com.baozi.treerecyclerview.viewholder;

import java.util.ArrayList;
import java.util.List;

public class ItemHelper {

    private static List<Class<? extends ItemData>> viewHolderTypes;

    static {
        viewHolderTypes = new ArrayList();
    }

    public static int getViewHolderTypesCount() {
        return viewHolderTypes.size();
    }

    public static int getViewHolderType(Class clazz) {
        return viewHolderTypes.indexOf(clazz);
    }

    public static Class getViewHolderType(int index) {
        return viewHolderTypes.get(index);
    }

    public static TreeItem getTreeItem(int ItemType) {
        try {
            return (TreeItem) getViewHolderType(ItemType).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addHolderType(Class<? extends ItemData> clazz) {
        if (!viewHolderTypes.contains(clazz)) {
            viewHolderTypes.add(clazz);
        }
    }
}