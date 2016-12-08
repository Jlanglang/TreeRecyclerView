package com.baozi.treerecyclerview.treeitem;

import com.baozi.treerecyclerview.viewholder.TreeAdapterItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class FiveItem extends TreeAdapterItem<String> {
    public FiveItem(String data) {
        super(data);
    }

    @Override
    protected List<TreeAdapterItem> initChildsList(String data) {
        return null;
    }

    @Override
    public int grade() {
        return 5;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {

    }
}
