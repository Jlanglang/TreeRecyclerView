package com.baozi.demo.item.clickload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

public class ClickLoadChildItem extends TreeItem<String> {
    @Override
    public int getLayoutId() {
        return R.layout.item_sort_child;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {

    }
}
