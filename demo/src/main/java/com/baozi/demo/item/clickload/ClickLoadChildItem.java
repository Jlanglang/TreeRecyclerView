package com.baozi.demo.item.clickload;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.util.Arrays;
import java.util.List;

public class ClickLoadChildItem extends TreeItem<String> {
    @Override
    public int getLayoutId() {
        return R.layout.item_sort_child;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {

    }
}
