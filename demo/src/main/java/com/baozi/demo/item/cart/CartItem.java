package com.baozi.demo.item.cart;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

/**
 * Created by a123 on 2018/6/5.
 */

public class CartItem extends TreeItem<Integer> {


    @Override
    public int getLayoutId() {
        return R.layout.item_cart_child;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        TreeItemGroup parentItem = getParentItem();
        if (parentItem instanceof CartGroupItem3) {
            viewHolder.setChecked(R.id.cb_ischeck, ((CartGroupItem3) parentItem).isChildSelect(this));
        }
        viewHolder.setText(R.id.tv_price, data + "");
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, RecyclerView.LayoutParams layoutParams, int position) {
        super.getItemOffsets(outRect, layoutParams, position);
        outRect.bottom = 1;
    }
}
