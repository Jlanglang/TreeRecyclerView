package com.baozi.treerecyclerview.widget;

import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSortItem;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 * 索引adapter
 */

public class TreeSortAdapter extends TreeRecyclerAdapter {
    private HashMap<Object, Integer> sortMap = new HashMap<>();

    @Override
    public void setDatas(List<TreeItem> datas) {
        super.setDatas(datas);
        List<TreeItem> treeItems = getDatas();
        int size = treeItems.size();
        for (int i = 0; i < size; i++) {
            TreeItem treeItem = treeItems.get(i);
            if (treeItem instanceof TreeSortItem) {
                sortMap.put(((TreeSortItem) treeItem).getSortKey(), i);
            }
        }
    }

    public int getSortIndex(Object o) {
        return sortMap.get(o);
    }
}
