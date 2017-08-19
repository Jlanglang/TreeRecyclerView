package com.baozi.demo.moudle.sortList;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSortItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/8/19.
 */

public class SortGroupItem extends TreeSortItem<Integer> {
    @Override
    protected List<TreeItem> initChildsList(Integer childs) {
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        for (int i = 0; i < childs; i++) {
            SortChildItem sortChildItem = new SortChildItem();
            sortChildItem.setData(i+"");
            treeItems.add(sortChildItem);
        }
        return treeItems;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_sort_group;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
            viewHolder.setText(R.id.tv_content, (String) getSortKey());
    }
}
