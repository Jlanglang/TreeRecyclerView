package com.baozi.demo.item.clickload

import com.baozi.demo.R
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.item.TreeItemGroup

import java.util.Arrays

class ClickLoadChildItem : TreeItem<String>() {
    override fun getLayoutId(): Int {
        return R.layout.item_sort_child
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {

    }
}
