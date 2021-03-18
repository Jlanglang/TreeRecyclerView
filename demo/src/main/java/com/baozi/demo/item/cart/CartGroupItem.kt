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

/**
 * Created by a123 on 2018/6/5.
 */
@TreeItemType(type = ["1"])
class CartGroupItem : TreeSelectItemGroup<CartBean>() {

    override fun initChild(data: CartBean): List<TreeItem<*>>? {
        val list = ArrayList<CartBean.CartBean2>()
        for (i in 0 until data.childSum) {
            list.add(CartBean.CartBean2(2))
        }
        return ItemHelperFactory.createItems(list, treeParentItem = this)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_cart_group
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {
        viewHolder.setText(R.id.cb_ischeck, "电脑(一级)")
        viewHolder.setChecked(R.id.cb_ischeck, isSelect)
        viewHolder.getView<CheckBox>(R.id.cb_ischeck).setOnClickListener { v ->
            selectAll(!isSelectAll, true)
            (viewHolder.itemView.context as CartAt).notifyPrice()
        }
        viewHolder.itemView.setPadding(0, 0, 0, 0)
    }

    init {
        isExpand = true
    }
}
