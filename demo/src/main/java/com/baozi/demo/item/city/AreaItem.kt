package com.baozi.demo.item.city

import android.graphics.Rect
import android.support.v7.widget.RecyclerView

import com.baozi.demo.R
import com.baozi.treerecyclerview.annotation.TreeItemType
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItem

/**
 *
 */
class AreaItem : TreeItem<ProvinceBean.CityBean.AreasBean>() {

    override fun getLayoutId(): Int {
        return R.layout.item_three
    }

    override fun onBindViewHolder(holder: ViewHolder) {
        holder.setText(R.id.tv_content, data?.areaName)
    }

    override fun getSpanSize(maxSpan: Int): Int {
        return maxSpan / 3
    }

    override fun getItemOffsets(outRect: Rect, layoutParams: RecyclerView.LayoutParams, position: Int) {
        super.getItemOffsets(outRect, layoutParams, position)
    }
}
