package com.baozi.demo.item.cart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.CheckBox;

import com.baozi.demo.R;
import com.baozi.demo.activity.CartActivity;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSelectItemGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by a123 on 2018/6/5.
 */
public class CartGroupItem3 extends TreeSelectItemGroup<CartBean3> {

    @Nullable
    @Override
    protected List<TreeItem> initChildList(CartBean3 data) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < data.childSum; i++) {
            list.add(new Random().nextInt(300));
        }
        return ItemHelperFactory.createItems(list, CartItem.class, this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_cart_group;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        viewHolder.setText(R.id.cb_ischeck, "我是三级");
        viewHolder.setChecked(R.id.cb_ischeck, isChildSelect());
        viewHolder.<CheckBox>getView(R.id.cb_ischeck).setOnClickListener((v) -> {
            selectAll(!isSelectAll());
            ((CartActivity) viewHolder.itemView.getContext()).notifyPrice();
        });
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        selectAll(!isSelectAll());
    }
}
