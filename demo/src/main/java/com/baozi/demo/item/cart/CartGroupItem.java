package com.baozi.demo.item.cart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.baozi.demo.R;
import com.baozi.demo.activity.CartActivity;
import com.baozi.demo.activity.CityListActivity;
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
        viewHolder.<CheckBox>getView(R.id.cb_ischeck).setOnCheckedChangeListener((v, isChecked) -> {
            selectAll(isChecked);
            ((CartActivity) viewHolder.itemView.getContext()).notifyPrice();
        });
    }

    @Nullable
    @Override
    protected List<TreeItem> initChildList(String data) {
        Integer[] strings = {100, 200, 300, 400};//假数据
        List<Integer> childs = Arrays.asList(strings);
        return ItemHelperFactory.createTreeItemList(childs, CartItem.class, this);
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        super.onClick(viewHolder);
    }

    @Override
    public boolean onInterceptClick(TreeItem child) {
        return super.onInterceptClick(child);
    }
}
