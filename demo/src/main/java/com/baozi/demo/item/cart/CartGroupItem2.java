package com.baozi.demo.item.cart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.CheckBox;

import com.baozi.demo.R;
import com.baozi.demo.activity.CartAt;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSelectItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a123 on 2018/6/5.
 */
public class CartGroupItem2 extends TreeSelectItemGroup<CartBean.CartBean2> {

    @Nullable
    @Override
    protected List<TreeItem> initChild(CartBean.CartBean2 data) {
        ArrayList<CartBean.CartBean2.CartBean3> list = new ArrayList<>();
        for (int i = 0; i < data.childSum; i++) {
            list.add(new CartBean.CartBean2.CartBean3(3));
        }
        return ItemHelperFactory.createItems(list, this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_cart_group;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        viewHolder.setText(R.id.cb_ischeck, "笔记本(二级)");
        viewHolder.setChecked(R.id.cb_ischeck, isSelect());
        viewHolder.<CheckBox>getView(R.id.cb_ischeck).setOnClickListener((v) -> {
            selectAll(!isSelectAll(), true);
            ((CartAt) viewHolder.itemView.getContext()).notifyPrice();
        });
        viewHolder.itemView.setPadding(20, 0, 0, 0);
    }

}
