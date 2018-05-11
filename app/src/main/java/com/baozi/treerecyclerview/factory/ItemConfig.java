package com.baozi.treerecyclerview.factory;

import android.util.SparseArray;

import com.baozi.treerecyclerview.TreeItemType;
import com.baozi.treerecyclerview.item.TreeItem;

import java.lang.annotation.Annotation;

public class ItemConfig {

    private static SparseArray<Class<? extends TreeItem>> treeViewHolderTypes;

    static {
        treeViewHolderTypes = new SparseArray<>();
    }

    public static Class<? extends TreeItem> getTreeViewHolderType(int type) {
        return treeViewHolderTypes.get(type);
    }

    public static void addTreeHolderType(int type, Class<? extends TreeItem> clazz) {
        if (null == clazz) {
            return;
        }
        treeViewHolderTypes.put(type, clazz);
    }

    @SafeVarargs
    public static void addTreeHolderType(Class<? extends TreeItem>... clazz) {
        for (Class<? extends TreeItem> zClass : clazz) {
            Annotation annotation = zClass.getAnnotation(TreeItemType.class);
            if (annotation != null) {
                int type = ((TreeItemType) annotation).type();
                Class<? extends TreeItem> aClass = treeViewHolderTypes.get(type);
                if (aClass == null) {
                    treeViewHolderTypes.put(type, zClass);
                } else if (!aClass.getSimpleName().equals(zClass.getSimpleName())) {
                    throw new IllegalStateException(zClass.getSimpleName() +
                            " type already exists and " + treeViewHolderTypes.get(type).getSimpleName());
                }
            }
        }
    }
}