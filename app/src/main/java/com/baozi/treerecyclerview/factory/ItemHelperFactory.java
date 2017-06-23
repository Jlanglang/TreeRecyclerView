package com.baozi.treerecyclerview.factory;

import android.support.annotation.NonNull;

import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.base.BaseItemData;
import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;
import com.baozi.treerecyclerview.view.TreeItemWapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/4/29.
 */

public class ItemHelperFactory {
    /**
     * 确定item的class类型,并且添加到了itemConfig,用该方法创建List<BaseItem>
     *
     * @param list
     * @return
     */
    public static List<BaseItem> createItemList(List<BaseItemData> list) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<BaseItem> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BaseItemData itemData = list.get(i);
            try {
                Class<? extends BaseItem> itemClass = ItemConfig.getViewHolderType(itemData.getViewItemType());
                if (itemClass != null) {
                    BaseItem item = itemClass.newInstance();
                    item.setData(itemData);
                    treeItemList.add(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return treeItemList;
    }

    /**
     * 不确定item的class类型,用该方法创建List<BaseItem>
     *
     * @param list
     * @return
     */
    public static <D> List<BaseItem> createItemList(List<D> list, Class<BaseItem> iClass) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<BaseItem> baseItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            D o = list.get(i);
            try {
                if (iClass != null) {
                    BaseItem baseItem = iClass.newInstance();
                    baseItem.setData(o);
                    baseItemList.add(baseItem);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baseItemList;
    }


    public static List<TreeItem> createTreeItemList(List<? extends BaseItemData> list, TreeItemGroup treeParentItem) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<TreeItem> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BaseItemData itemData = list.get(i);
            try {
                TreeItem treeItem;
                int viewItemType = itemData.getViewItemType();
                //判断是否是TreeItem的子类
                if (ItemConfig.getTreeViewHolderType(viewItemType) != null) {
                    Class<? extends TreeItem> treeItemClass = ItemConfig.getTreeViewHolderType(viewItemType);
                    treeItem = treeItemClass.newInstance();
                } else {
                    Class<? extends BaseItem> baseItmeClass = ItemConfig.getViewHolderType(viewItemType);
                    //判断是否是BaseItem的子类
                    treeItem = new TreeItemWapper(baseItmeClass.newInstance());
                }
                treeItem.setData(itemData);
                treeItem.setParentItem(treeParentItem);
                treeItemList.add(treeItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return treeItemList;
    }

    public static <D> List<TreeItem> createTreeItemList(List<D> list, Class<? extends TreeItem> iClass, TreeItemGroup treeParentItem) {
        if (null == list) {
            return null;
        }
        int size = list.size();
        ArrayList<TreeItem> treeItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            D itemData = list.get(i);
            try {
                if (iClass != null) {
                    TreeItem<D> treeItem = iClass.newInstance();
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
     * 确定item的class类型,并且添加到了itemConfig,用该方法创建BaseItem
     *
     * @return
     */
    public static <D extends BaseItemData> BaseItem createBaseItem(D d) {
        BaseItem baetItem = null;
        try {
            Class<? extends BaseItem> itemClass = ItemConfig.getViewHolderType(d.getViewItemType());
            if (itemClass != null) {
                baetItem = itemClass.newInstance();
                baetItem.setData(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baetItem;
    }

    /**
     * 确定item的class类型,并且添加到了itemConfig,用该方法创建TreeItem
     *
     * @return
     */
    public static <D extends BaseItemData> TreeItem createTreeItem(D d) {
        TreeItem treeItem = null;
        try {
            Class<? extends BaseItem> itemClass = ItemConfig.getViewHolderType(d.getViewItemType());
            if (itemClass != null) {
                treeItem = (TreeItem) itemClass.newInstance();
                treeItem.setData(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeItem;
    }


    @NonNull
    public static ArrayList<TreeItem> getChildItemsWithType(TreeItemGroup treeItemGroup, TreeRecyclerViewType type) {
        ArrayList<TreeItem> baseItems = new ArrayList<>();
        List allChild = treeItemGroup.getChilds();
        int childCount = treeItemGroup.getChildCount();
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
                            list = ((TreeItemGroup) baseItem).getExpandChilds();
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
    public static ArrayList<TreeItem> getChildItemsWithType(List<TreeItem> treeItems, TreeRecyclerViewType type) {
        ArrayList<TreeItem> baseItems = new ArrayList<>();
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
