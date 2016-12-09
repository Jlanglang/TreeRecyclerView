package com.baozi.treerecyclerview.treeitem;

import com.baozi.treerecyclerview.R;
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
    protected int initLayoutId() {
        return R.layout.item_five;
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder) {
        if (layoutId == R.layout.itme_one) {
            holder.setText(R.id.tv_content, "我是第二种五级");
        } else if (layoutId == R.layout.item_five) {
            holder.setText(R.id.tv_content, "我是第一种五级");
        }
    }
}
