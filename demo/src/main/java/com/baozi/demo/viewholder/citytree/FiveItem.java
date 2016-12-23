package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.viewholder.TreeItem;
import com.baozi.treerecyclerview.viewholder.TreeParentItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

/**
 */
public class FiveItem extends TreeItem<String> {
    public FiveItem(String data) {
        super(data);
    }
    @Override
    protected int initLayoutId() {
        return R.layout.item_five;
    }

    @Override
    protected int initSpansize() {
        return 2;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        if (layoutId == R.layout.itme_one) {
            holder.setText(R.id.tv_content, "我是第一种五级");
        } else if (layoutId == R.layout.item_five) {
            holder.setText(R.id.tv_content, "我是第二种五级");
        } else if (layoutId == R.layout.item_two) {
            holder.setText(R.id.tv_content, "我是第三种五级");
        }
    }
}
