package com.baozi.demo.item.tbhome

import com.baozi.demo.R
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItem

/**
 * 模板A
 */
class GoodsItem : TreeItem<Any>() {
    override fun getLayoutId(): Int {
        return R.layout.item_tb_goods
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {

    }

    override fun getSpanSize(maxSpan: Int): Int {
        return super.getSpanSize(maxSpan) / 3
    }
}
