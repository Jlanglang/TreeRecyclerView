package com.baozi.treerecyclerview.factory;

import android.util.SparseArray;

import com.baozi.treerecyclerview.BindItemType;
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
            Annotation annotation = zClass.getAnnotation(BindItemType.class);
            if (annotation != null) {
                int type = ((BindItemType) annotation).type();
                Class<? extends TreeItem> typeClass = treeViewHolderTypes.get(type);
                if (typeClass == null) {
                    treeViewHolderTypes.put(type, zClass);
                } else if (typeClass != zClass) {
                    throw new IllegalStateException(zClass.getSimpleName() +
                            " type already exists and " + treeViewHolderTypes.get(type).getSimpleName());
                }
            }
        }
    }
}