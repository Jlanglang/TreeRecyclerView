package com.baozi.demo.item.cart

import android.widget.CheckBox

import com.baozi.demo.R
import com.baozi.demo.activity.CartAt
import com.baozi.treerecyclerview.annotation.TreeItemType
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.item.TreeSelectItemGroup

import java.util.ArrayList
import java.util.Random

/**
 * Created by a123 on 2018/6/5.
 */
@TreeItemType(type = ["1"])
class CartGroupItem3 : TreeSelectItemGroup<CartBean.CartBean2.CartBean3>() {

    override fun initChild(data: CartBean.CartBean2.CartBean3): List<TreeItem<*>>? {
        val list = ArrayList<Int>()
        for (i in 0 until data.childSum) {
            list.add(Random().nextInt(300))
        }
        return ItemHelperFactory.createItems(list, CartItem::class.java, this)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_cart_group
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {
        viewHolder.setText(R.id.cb_ischeck, "联想(三级)")
        viewHolder.setChecked(R.id.cb_ischeck, isSelect)
        viewHolder.getView<CheckBox>(R.id.cb_ischeck).setOnClickListener { v ->
            selectAll(!isSelectAll, true)
            (viewHolder.itemView.context as CartAt).notifyPrice()
        }
        viewHolder.itemView.setPadding(40, 0, 0, 0)
    }

    override fun onInterceptClick(child: TreeItem<*>): Boolean {
        selectItem(child, true)
        return super.onInterceptClick(child)
    }
}
