package com.baozi.demo.item.city


import com.baozi.demo.R
import com.baozi.demo.activity.CityAt
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItem

/**
 *
 */
class AreaItem : TreeItem<ProvinceBean.CityBean.AreasBean>() {

    override fun getLayoutId(): Int {
        return R.layout.item_three
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {
        viewHolder.setText(R.id.tv_content, data?.areaName)
    }

    override fun getSpanSize(maxSpan: Int): Int {
        return maxSpan / 3
    }

    override fun onClick(viewHolder: ViewHolder) {
        super.onClick(viewHolder)
        //通过这样拿到Activity的引用
        val tag = itemManager?.tag as? CityAt?
        tag?.show()
    }
}
