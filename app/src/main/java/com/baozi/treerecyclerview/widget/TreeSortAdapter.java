package com.baozi.treerecyclerview.widget;

import com.baozi.treerecyclerview.adpater.ItemManager;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSortItem;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2017/8/8 0008.
 * 索引adapter
 */

public class TreeSortAdapter extends TreeRecyclerAdapter {
    private final HashMap<Object, Integer> sortMap = new HashMap<>();
    private TreeSortManageWapper manageWapper;

    @Override
    public void setDatas(List<TreeItem> datas) {
        super.setDatas(datas);
        List<TreeItem> treeItems = getDatas();
        getItemManager().updateSorts(treeItems);
    }


    public int getSortIndex(Object o) {
        return sortMap.get(o);
    }

    @Override
    public TreeSortManageWapper getItemManager() {
        if (manageWapper == null) {
            manageWapper = new TreeSortManageWapper(this, super.getItemManager());
        }
        return manageWapper;
    }

    public class TreeSortManageWapper extends ItemManager<TreeItem> {
        ItemManager<TreeItem> manager;

        public TreeSortManageWapper(BaseRecyclerAdapter adapter, ItemManager<TreeItem> manager) {
            super(adapter);
            this.manager = manager;
        }

        @Override
        public void addItem(TreeItem treeItem) {
            manager.addItem(treeItem);
            updataSort(manager.getItemPosition(treeItem), treeItem);
        }


        @Override
        public void addItem(int i, TreeItem treeItem) {
            manager.addItem(i, treeItem);
            updataSort(i, treeItem);
        }

        @Override
        public void addItems(List<TreeItem> list) {
            manager.addItems(list);
            updateSorts(list);
        }

        @Override
        public void addItems(int i, List<TreeItem> list) {
            manager.addItems(i, list);
            updateSorts(list);
//            int size = list.size();
//            for (int x = 0; x < size; x++) {
//                TreeItem item = list.get(x);
//                if (item instanceof SortTreeItem) {
//                    sortMap.put(((SortTreeItem) item).getSortKey(), manager.getItemPosition(item));
//                }
//            }
        }

        @Override
        public void removeItem(TreeItem treeItem) {
            manager.removeItem(treeItem);
            updataSort(getItemPosition(treeItem), treeItem);
//            if (treeItem instanceof SortTreeItem) {
//                sortMap.remove(((SortTreeItem) treeItem).getSortKey());
//            }
        }

        @Override
        public void removeItem(int i) {
            TreeItem item = manager.getItem(i);
            if (item instanceof TreeSortItem) {
                sortMap.remove(((TreeSortItem) item).getSortKey());
            }
            manager.removeItem(i);
        }

        @Override
        public void removeItems(List<TreeItem> list) {
            manager.removeItems(list);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeItem item = list.get(i);
                if (item instanceof TreeSortItem) {
                    sortMap.remove(((TreeSortItem) item).getSortKey());
                }
            }
        }

        @Override
        public void replaceItem(int i, TreeItem treeItem) {
            manager.replaceItem(i, treeItem);
            updataSort(i, treeItem);
//            if (treeItem instanceof SortTreeItem) {
//                sortMap.put(((SortTreeItem) treeItem).getSortKey(), i);
//            }
        }

        @Override
        public void replaceAllItem(List<TreeItem> list) {
            manager.replaceAllItem(list);
            updateSorts(list);
//            int size = list.size();
//            for (int i = 0; i < size; i++) {
//                TreeItem item = list.get(i);
//                if (item instanceof SortTreeItem) {
//                    sortMap.put(((SortTreeItem) item).getSortKey(), manager.getItemPosition(item));
//                }
//            }
        }

        @Override
        public TreeItem getItem(int i) {
            return manager.getItem(i);
        }

        @Override
        public int getItemPosition(TreeItem treeItem) {
            return manager.getItemPosition(treeItem);
        }

        public void updateSorts(List<TreeItem> treeItems) {
            int size = treeItems.size();
            for (int i = 0; i < size; i++) {
                TreeItem treeItem = treeItems.get(i);
                if (treeItem instanceof TreeSortItem) {
                    sortMap.put(((TreeSortItem) treeItem).getSortKey(), getItemPosition(treeItem));
                }
            }
        }

        public void updataSort(TreeItem treeItem) {
            if (treeItem instanceof TreeSortItem) {
                sortMap.put(((TreeSortItem) treeItem).getSortKey(), getItemPosition(treeItem));
            }
        }

        public void updataSort(int position, TreeItem treeItem) {
            if (treeItem instanceof TreeSortItem) {
                sortMap.put(((TreeSortItem) treeItem).getSortKey(), position);
            }
        }
    }
}
