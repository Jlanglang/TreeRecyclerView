package com.baozi.demo.item.mine;

import android.support.annotation.NonNull;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

public class MineItem extends TreeItem<String> {
    @Override
    public int getLayoutId() {
        return R.layout.item_mine;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        viewHolder.setText(R.id.tv_name, data);
    }
}
