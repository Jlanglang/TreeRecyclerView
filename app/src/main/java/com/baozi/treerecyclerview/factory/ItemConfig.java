package com.baozi.treerecyclerview.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseArray;

import com.baozi.treerecyclerview.annotation.TreeDataType;
import com.baozi.treerecyclerview.annotation.TreeItemType;
import com.baozi.treerecyclerview.item.TreeItem;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

public class ItemConfig {
    /**
     * type,TreeClass映射
     */
    private static final HashMap<String, Class<? extends TreeItem>> treeViewHolderTypes = new HashMap<>();
    /**
     * TreeDataType映射
     */
    private static final HashMap<Class, TreeDataType> treeDataTypeMap = new HashMap<>();
    /**
     * TreeItemType映射
     */
    private static final HashMap<Class, TreeItemType> treeItemTypeMap = new HashMap<>();

    public static Class<? extends TreeItem> getTreeViewHolderType(String type) {
        return treeViewHolderTypes.get(type);
    }

    public static void register(String type, Class<? extends TreeItem> clazz) {
        if (null == clazz) {
            return;
        }
        Class<? extends TreeItem> typeClass = treeViewHolderTypes.get(type);
        if (typeClass == null) {
            treeViewHolderTypes.put(type, clazz);
            return;
        }
        if (clazz != typeClass) {//如果该type,已经添加了,则抛出异常
            throw new IllegalStateException("该type映射了" + typeClass.getSimpleName() + "不能再映射其他TreeItem");
        }
    }

    public static void register(Class<? extends TreeItem> zClass) {
        if (treeItemTypeMap.get(zClass) != null) {
            return;
        }
        TreeItemType annotation = zClass.getAnnotation(TreeItemType.class);
        if (annotation != null) {
            treeItemTypeMap.put(zClass, annotation);
            String[] typeList = annotation.type();
            for (String type : typeList) {
                register(type, zClass);
            }
        }
    }

    @SafeVarargs
    public static void register(@NonNull Class<? extends TreeItem>... clazz) {
        for (Class<? extends TreeItem> zClass : clazz) {
            register(zClass);
        }
    }

    /**
     * 获取TreeItem的Class
     *
     * @param data
     * @return
     */
    @Nullable
    public static Class<? extends TreeItem> getTypeClass(Object data) {
        Class<?> dataClass = data.getClass();
        //先判断是否继承了ItemData,适用于跨模块获取
        //判断是否使用注解绑定了ItemClass,适用当前模块
        TreeDataType treeDataType = treeDataTypeMap.get(dataClass);
        if (treeDataType == null) {
            treeDataType = dataClass.getAnnotation(TreeDataType.class);
        }
        if (treeDataType != null) {
            //缓存注解
            treeDataTypeMap.put(dataClass, treeDataType);

            String key = treeDataType.bindField();
            if (!TextUtils.isEmpty(key)) {
                try {
                    Field field = dataClass.getField(key);
                    String type = field.get(data).toString();
                    Class<? extends TreeItem> itemClass = getTreeViewHolderType(type);
                    return itemClass;
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return treeDataType.iClass();
        }
        return null;
    }
}