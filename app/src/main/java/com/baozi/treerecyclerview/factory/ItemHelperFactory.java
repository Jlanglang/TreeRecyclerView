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

    @Deprecated
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
    @Deprecated
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
        int size = list.size();
        ArrayList<TreeItem> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            try {
                Object itemData = list.get(i);
                Class<? extends TreeItem> iClass =
                        getTypeClass(itemData);
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
    public static ArrayList<TreeItem> getChildItemsWithType(@Nullable TreeItemGroup itemGroup, @Nullable TreeRecyclerType type) {
        ArrayList<TreeItem> baseItems = new ArrayList<>();
        List allChild = itemGroup.getChild();
        if (allChild == null) return baseItems;
        int childCount = allChild.size();
        for (int i = 0; i < childCount; i++) {
            //下级
            TreeItem baseItem = (TreeItem) allChild.get(i);
            baseItems.add(baseItem);
            //判断下级是否为TreeItemGroup
            if (baseItem instanceof TreeItemGroup) {
                List list = null;
                switch (type) {
                    case SHOW_ALL:
                        //调用下级的getAllChilds遍历,相当于递归遍历
                        list = ((TreeItemGroup) baseItem).getAllChilds();
                        break;
                    case SHOW_EXPAND:
                        //根据isExpand,来决定是否展示
                        if (((TreeItemGroup) baseItem).isExpand()) {
                            list = ((TreeItemGroup) baseItem).getExpandChild();
                        }
                        break;
                    case SHOW_DEFUTAL:
                        break;
                }
                if (list != null && list.size() > 0) {
                    baseItems.addAll(list);
                }
            }
        }
        return baseItems;
    }

    @NonNull
    public static ArrayList<TreeItem> getChildItemsWithType(@NonNull List<TreeItem> treeItems, @NonNull TreeRecyclerType type) {
        if (type == TreeRecyclerType.SHOW_DEFUTAL) {
            return (ArrayList<TreeItem>) treeItems;
        }
        ArrayList<TreeItem> baseItems = new ArrayList<TreeItem>();
        int childCount = treeItems.size();
        for (int i = 0; i < childCount; i++) {
            TreeItem treeItem = treeItems.get(i);
            baseItems.add(treeItem);
            if (treeItem instanceof TreeItemGroup) {
                ArrayList<TreeItem> childItems = getChildItemsWithType((TreeItemGroup) treeItem, type);
                if (!childItems.isEmpty()) {
                    baseItems.addAll(childItems);
                }
            }
        }
        return baseItems;
    }
}
