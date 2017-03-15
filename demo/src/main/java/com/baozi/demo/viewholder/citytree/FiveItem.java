package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.adpater.ViewHolder;

/**
 */
public class FiveItem extends TreeItem<String> {

    public FiveItem(String data) {
        super(data);
    }

    @Override
    public int getLayoutId() {
        if (getSpanSize() == 0) {
            return R.layout.item_five;
        } else {
            return R.layout.item_two;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, "我是第二种五级");
    }
}
