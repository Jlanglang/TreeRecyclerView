package com.baozi.treerecyclerview.factory;

import android.util.SparseArray;

import com.baozi.treerecyclerview.annotation.TreeItemType;
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

    @Deprecated
    public static void addTreeHolderType(int type, Class<? extends TreeItem> clazz) {
        if (null == clazz) {
            return;
        }
        treeViewHolderTypes.put(type, clazz);
    }

    @Deprecated
    public static void addTreeHolderType(Class<? extends TreeItem>... clazz) {
        for (Class<? extends TreeItem> zClass : clazz) {
            Annotation annotation = zClass.getAnnotation(TreeItemType.class);
            if (annotation != null) {
                int type = ((TreeItemType) annotation).type();
                if (type == -1) {
                    continue;
                }
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

    /**
     * 注册classType
     * 类必须使用BindItemType注解，才能注册
     *
     * @param clazz
     */
    public static void registerTreeItem(Class<? extends TreeItem>... clazz) {
        addTreeHolderType(clazz);
    }
}