package com.baozi.demo.item.city

import android.graphics.Rect
import android.support.v7.widget.RecyclerView

import com.baozi.demo.R
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItemGroup
import com.baozi.treerecyclerview.manager.ItemManager

/**
 * Created by baozi on 2016/12/8.
 */
class ProvinceItem : TreeItemGroup<ProvinceBean>() {

    override var isCanExpand: Boolean
        get() = true
        set(value) {
            super.isCanExpand = value
        }

    public override fun initChild(data: ProvinceBean): List<TreeItem<*>>? {
        val items = ItemHelperFactory.createItems(data.citys, treeParentItem = this)
        for (i in items.indices) {
            val treeItem = items[i] as TreeItemGroup<*>
            treeItem.isExpand=false
        }
        return items
    }

    override fun getLayoutId(): Int {
        return R.layout.itme_one
    }

    override fun onBindViewHolder(holder: ViewHolder) {
        holder.setText(R.id.tv_content, data?.provinceName)
    }

    override fun getItemOffsets(outRect: Rect, layoutParams: RecyclerView.LayoutParams, position: Int) {
        super.getItemOffsets(outRect, layoutParams, position)
        outRect.bottom = 1
    }
}
