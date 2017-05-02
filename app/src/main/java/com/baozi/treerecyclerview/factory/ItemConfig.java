package com.baozi.treerecyclerview.factory;

import com.baozi.treerecyclerview.base.BaseItem;

import java.util.HashMap;

public class ItemConfig {

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

    public static void addHolderType(int type, Class<? extends BaseItem> clazz) {
        if (null == clazz) {
            return;
        }
        viewHolderTypes.put(type, clazz);
    }
}