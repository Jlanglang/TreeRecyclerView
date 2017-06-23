package com.baozi.treerecyclerview.factory;

import android.util.SparseArray;

import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.view.TreeItem;

import java.util.HashMap;

public class ItemConfig {

    private static SparseArray<Class<? extends BaseItem>> baseviewHolderTypes;
    private static SparseArray<Class<? extends TreeItem>> treeviewHolderTypes;

    static {
        baseviewHolderTypes = new SparseArray<>();
        treeviewHolderTypes = new SparseArray<>();
    }

    public static int getViewHolderTypesCount() {
        return baseviewHolderTypes.size();
    }

    public static Class<? extends BaseItem> getViewHolderType(int type) {
        return baseviewHolderTypes.get(type);
    }

    public static Class<? extends TreeItem> getTreeViewHolderType(int type) {
        return treeviewHolderTypes.get(type);
    }

    public static void addHolderType(int type, Class<? extends BaseItem> clazz) {
        if (null == clazz) {
            return;
        }
        baseviewHolderTypes.put(type, clazz);
    }

    public static void addTreeHolderType(int type, Class<? extends TreeItem> clazz) {
        if (null == clazz) {
            return;
        }
        treeviewHolderTypes.put(type, clazz);
    }
}