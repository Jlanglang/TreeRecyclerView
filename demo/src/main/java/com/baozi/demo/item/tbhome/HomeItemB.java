package com.baozi.demo.item.tbhome;

import androidx.annotation.NonNull;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

/**
 * 模板A
 */
public class HomeItemB extends TreeItem {
    @Override
    public int getLayoutId() {
        return R.layout.item_tb_home_b;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {

    }

    @Override
    public int getSpanSize(int maxSpan) {
        return maxSpan/ 3 * 2;
    }
}
