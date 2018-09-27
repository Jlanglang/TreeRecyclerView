package com.baozi.demo.item.cart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSelectItemGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by a123 on 2018/6/5.
 */

public class CartGroupItem extends TreeSelectItemGroup<String> {

    @Override
    public int getLayoutId() {
        return R.layout.item_cart_group;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        viewHolder.setChecked(R.id.cb_ischeck, isChildCheck());
    }

    @Nullable
    @Override
    protected List<TreeItem> initChildList(String data) {
        String[] strings = {"1", "1", "1", "1"};//假数据
        List<String> childs = Arrays.asList(strings);
        return ItemHelperFactory.createTreeItemList(childs, CartItem.class, this);
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        super.onClick(viewHolder);
        selectAll(!isSelectAll());
        getItemManager().notifyDataChanged();
    }
}
