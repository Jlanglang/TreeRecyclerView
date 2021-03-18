package com.baozi.demo.item.city

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

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

    public override fun initChild(data: ProvinceBean): List<TreeItem<*>>? {
        return ItemHelperFactory.createItems(data.citys, treeParentItem = this)
    }

    override fun getLayoutId(): Int {
        return R.layout.itme_one
    }

    override fun onBindViewHolder(holder: ViewHolder) {
        holder.setText(R.id.tv_content, data?.provinceName)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView?, state: RecyclerView.State?, position: Int) {
        super.getItemOffsets(outRect, view, parent, state, position)
        outRect.bottom = 1
    }
}
