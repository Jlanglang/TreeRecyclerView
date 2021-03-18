package com.baozi.demo.item.cart

import android.graphics.Rect
import android.support.v7.widget.RecyclerView

import com.baozi.demo.R
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.item.TreeItemGroup

/**
 * Created by a123 on 2018/6/5.
 */

class CartItem : TreeItem<Int>() {


    override fun getLayoutId(): Int {
        return R.layout.item_cart_child
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {
        val parentItem = parentItem
        if (parentItem is CartGroupItem3) {
            viewHolder.setChecked(R.id.cb_ischeck, parentItem.isSelect(this))
        }
        viewHolder.setText(R.id.tv_price, data.toString() + "")
        viewHolder.itemView.setPadding(50, 0, 0, 0)

    }


    override fun getItemOffsets(outRect: Rect, layoutParams: RecyclerView.LayoutParams, position: Int) {
        super.getItemOffsets(outRect, layoutParams, position)
        outRect.bottom = 1
    }
}
