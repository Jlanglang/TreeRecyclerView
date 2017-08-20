package com.baozi.treerecyclerview.factory;

import android.util.SparseArray;

import com.baozi.treerecyclerview.item.TreeItem;

public class ItemConfig {

    private static SparseArray<Class<? extends TreeItem>> treeviewHolderTypes;

    static {
        treeviewHolderTypes = new SparseArray<>();
    }

    public static Class<? extends TreeItem> getTreeViewHolderType(int type) {
        return treeviewHolderTypes.get(type);
    }

    public static void addTreeHolderType(int type, Class<? extends TreeItem> clazz) {
        if (null == clazz) {
            return;
        }
        treeviewHolderTypes.put(type, clazz);
    }
}