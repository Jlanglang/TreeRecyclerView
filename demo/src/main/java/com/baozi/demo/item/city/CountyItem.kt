package com.baozi.demo.item.city

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

import com.baozi.demo.R
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.item.TreeItemGroup

/**
 *
 */
class CountyItem : TreeItemGroup<ProvinceBean.CityBean>() {
    init {
        isExpand = true
    }

    public override fun initChild(data: ProvinceBean.CityBean): List<TreeItem<*>>? {
        return ItemHelperFactory.createItems(data.areas, treeParentItem = this)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_two
    }

    override fun onBindViewHolder(holder: ViewHolder) {
        holder.setText(R.id.tv_content, data?.cityName)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView?,
                                state: RecyclerView.State?, position: Int) {
        super.getItemOffsets(outRect, view, parent, state, position)
        outRect.top = 1
    }
}
