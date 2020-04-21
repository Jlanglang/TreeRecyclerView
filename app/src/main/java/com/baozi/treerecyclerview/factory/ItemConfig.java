package com.baozi.treerecyclerview.factory;

import androidx.annotation.NonNull;
import android.util.SparseArray;

import com.baozi.treerecyclerview.annotation.TreeItemType;
import com.baozi.treerecyclerview.item.TreeItem;

public class ItemConfig {

    private static final SparseArray<Class<? extends TreeItem>> treeViewHolderTypes = new SparseArray<>();

    public static Class<? extends TreeItem> getTreeViewHolderType(int type) {
        return treeViewHolderTypes.get(type);
    }

    public static void register(int type, Class<? extends TreeItem> clazz) {
        if (null == clazz) {
            return;
        }
        treeViewHolderTypes.put(type, clazz);
    }

    public static void register(Class<? extends TreeItem> zClass) {
        TreeItemType annotation = zClass.getAnnotation(TreeItemType.class);
        if (annotation != null) {
            int[] typeList = annotation.type();
            for (Integer type : typeList) {
                Class<? extends TreeItem> typeClass = treeViewHolderTypes.get(type);
                if (typeClass == null) {
                    treeViewHolderTypes.put(type, zClass);
                    continue;
                }
                if (zClass != typeClass) {//如果该type,已经添加了,则抛出异常
                    throw new IllegalStateException("该type映射了一个TreeItemClass,不能再映射其他TreeItemClass");
                }
            }
        }
    }

    @SafeVarargs
    public static void register(@NonNull Class<? extends TreeItem>... clazz) {
        for (Class<? extends TreeItem> zClass : clazz) {
            register(zClass);
        }
    }

}