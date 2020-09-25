package com.baozi.demo.item.tbhome

import com.baozi.demo.R
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItem

/**
 * 模板F
 */
class HomeItemF : TreeItem<Any>() {
    override fun getLayoutId(): Int {
        return R.layout.item_tb_home_f
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {

    }

    override fun getSpanSize(maxSpan: Int): Int {
        return maxSpan / 3
    }
}
