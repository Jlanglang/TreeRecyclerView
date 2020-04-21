package com.baozi.demo.item.cart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.CheckBox;

import com.baozi.demo.R;
import com.baozi.demo.activity.CartAt;
import com.baozi.treerecyclerview.annotation.TreeItemType;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSelectItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a123 on 2018/6/5.
 */
@TreeItemType(type = {1})
public class CartGroupItem extends TreeSelectItemGroup<CartBean> {

    @Nullable
    @Override
    protected List<TreeItem> initChild(CartBean data) {
        ArrayList<CartBean.CartBean2> list = new ArrayList<>();
        for (int i = 0; i < data.childSum; i++) {
            list.add(new CartBean.CartBean2(2));
        }
        return ItemHelperFactory.createItems(list, this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_cart_group;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        viewHolder.setText(R.id.cb_ischeck, "电脑(一级)");
        viewHolder.setChecked(R.id.cb_ischeck, isSelect());
        viewHolder.<CheckBox>getView(R.id.cb_ischeck).setOnClickListener((v) -> {
            selectAll(!isSelectAll(), true);
            ((CartAt) viewHolder.itemView.getContext()).notifyPrice();
        });
        viewHolder.itemView.setPadding(0, 0, 0, 0);
    }

}
