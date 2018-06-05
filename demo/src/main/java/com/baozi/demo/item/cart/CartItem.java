package com.baozi.demo.item.cart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.util.List;

/**
 * Created by a123 on 2018/6/5.
 */

public class CartItem extends TreeItem<String> {

    @Override
    public int getLayoutId() {
        return R.layout.item_cart_child;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {

    }

}
