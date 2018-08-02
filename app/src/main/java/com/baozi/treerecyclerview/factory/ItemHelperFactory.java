package com.baozi.treerecyclerview.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baozi.treerecyclerview.annotation.TreeItemClass;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.base.BaseItemData;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;
import com.baozi.treerecyclerview.item.TreeSortItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/4/29.
 */

public class ItemHelperFactory {

    public static List<TreeItem> createItems(@Nullable List list, @Nullable TreeItemGroup treeParentItem) {
        if (null == list) {
            return null;
        }
        ArrayList<TreeItem> treeItemList = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                Object itemData = list.get(i);
                Class<? extends TreeItem> treeItemClass = getTypeClass(itemData);
                TreeItem treeItem;
                //判断是否是TreeItem的子类
                if (treeItemClass != null) {
                    treeItem = treeItemClass.newInstance();
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
     * 获取ItemClass
     *
     * @param itemData
     * @return
     */
    private static Class<? extends TreeItem> getTypeClass(Object itemData) {
        Class<? extends TreeItem> treeItemClass = null;
        //先判断是否继承了ItemData,适用于跨模块获取
        if (itemData instanceof BaseItemData) {
            int viewItemType = ((BaseItemData) itemData).getViewItemType();
            treeItemClass = ItemConfig.getTreeViewHolderType(viewItemType);
        } else {
            //判断是否使用注解绑定了ItemClass,适用当前模块
            TreeItemClass annotation = itemData.getClass().getAnnotation(TreeItemClass.class);
            if (annotation != null) {
                treeItemClass = annotation.iClass();
            }
        }
        return treeItemClass;
    }

    public static List<TreeItem> createTreeItemList(@Nullable List list, Class<? extends TreeItem> iClass) {
        return createTreeItemList(list, iClass, null);
    }

    public static List<TreeItem> createTreeItemList(@Nullable List list, Class<? extends TreeItem> iClass, @Nullable TreeItemGroup treeParentItem) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<TreeItem> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            try {
                Object itemData = list.get(i);
                if (iClass != null) {
                    TreeItem treeItem = iClass.newInstance();
                    treeItem.setData(itemData);
                    treeItem.setParentItem(treeParentItem);
                    treeItemList.add(treeItem);
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return treeItemList;
    }

    /**
     * 创建排序List
     *
     * @param list
     * @param sortKey
     * @param treeParentItem
     * @return
     */
    public static List<TreeItem> createTreeSortList(@Nullable List list, Class<? extends TreeSortItem> iClass, Object sortKey, @Nullable TreeItemGroup treeParentItem) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<TreeItem> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            try {
                Object itemData = list.get(i);
                if (iClass != null) {
                    TreeSortItem sortItem = iClass.newInstance();
                    sortItem.setData(itemData);
                    sortItem.setSortKey(sortKey);
                    sortItem.setParentItem(treeParentItem);
                    treeItemList.add(sortItem);
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return treeItemList;
    }

    /**
     * 创建排序List
     *
     * @param list
     * @param sortKey
     * @param treeParentItem
     * @return
     */
    public static List<TreeItem> createTreeSortList(@Nullable List list, Object sortKey, @Nullable TreeItemGroup treeParentItem) {
        if (null == list) {
            return null;
        }
        ArrayList<TreeItem> treeItemList = new ArrayList<>();

        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                Object itemData = list.get(i);
                Class<? extends TreeItem> iClass = getTypeClass(itemData);
                if (iClass != null && iClass == TreeSortItem.class) {
                    TreeSortItem sortItem = (TreeSortItem) iClass.newInstance();
                    sortItem.setData(itemData);
                    sortItem.setSortKey(sortKey);
                    sortItem.setParentItem(treeParentItem);
                    treeItemList.add(sortItem);
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return treeItemList;
    }


    /**
     * 确定item的class类型,并且添加到了itemConfig,用该方法创建TreeItem
     *
     * @return
     */
    public static <D> TreeItem createTreeItem(D d) {
        TreeItem treeItem = null;
        try {
            Class<? extends TreeItem> itemClass;
            if (d instanceof BaseItemData) {
                itemClass = ItemConfig.getTreeViewHolderType(((BaseItemData) d).getViewItemType());
            } else {
                itemClass = getTypeClass(d);
            }
            if (itemClass != null) {
                treeItem = itemClass.newInstance();
                treeItem.setData(d);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return treeItem;
    }

    /**
     * 根据TreeRecyclerType获取子item集合,不包含TreeItemGroup自身
     *
     * @param itemGroup
     * @param type
     * @return
     */
    @NonNull
    public static ArrayList<TreeItem> getChildItemsWithType(@Nullable TreeItemGroup itemGroup, @NonNull TreeRecyclerType type) {
        if (itemGroup == null) {
            return new ArrayList();
        }
        return getChildItemsWithType(itemGroup.getChild(), type);
    }

    @NonNull
    public static ArrayList<TreeItem> getChildItemsWithType(@Nullable List<TreeItem> items, @NonNull TreeRecyclerType type) {
        ArrayList<TreeItem> returnItems = new ArrayList<>();
        if (items == null) {
            return returnItems;
        }
        int childCount = items.size();
        for (int i = 0; i < childCount; i++) {
            TreeItem childItem = items.get(i);//获取当前一级
            returnItems.add(childItem);
            if (childItem instanceof TreeItemGroup) {//获取下一级
                List list = null;
                switch (type) {
                    case SHOW_ALL:
                        //调用下级的getAllChild遍历,相当于递归遍历
                        list = getChildItemsWithType((TreeItemGroup) childItem, type);
                        break;
                    case SHOW_EXPAND:
                        //根据isExpand,来决定是否展示
                        if (((TreeItemGroup) childItem).isExpand()) {
                            list = getChildItemsWithType((TreeItemGroup) childItem, type);
                        }
                        break;
                }
                if (list != null && list.size() > 0) {
                    returnItems.addAll(list);
                }
            }

        }
        return returnItems;
    }
}
