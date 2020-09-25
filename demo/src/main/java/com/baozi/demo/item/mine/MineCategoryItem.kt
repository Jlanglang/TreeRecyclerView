package com.baozi.demo.item.mine

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.text.TextUtils

import com.baozi.demo.R
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.item.TreeItem

/**
 * 分类
 */
class MineCategoryItem : TreeItem<MineCategoryBean>() {
    override fun getLayoutId(): Int {
        return R.layout.item_mine_category
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {
        viewHolder.setText(R.id.tv_content, data.content)

        viewHolder.setVisible(R.id.tv_title, !TextUtils.isEmpty(data.title))
        viewHolder.setText(R.id.tv_title, data.title)

        viewHolder.setVisible(R.id.iv_content, !TextUtils.isEmpty(data.url))

    }

    override fun getSpanSize(maxSpan: Int): Int {
        return maxSpan / 4
    }

    override fun getItemOffsets(outRect: Rect, layoutParams: RecyclerView.LayoutParams, position: Int) {
        super.getItemOffsets(outRect, layoutParams, position)
        if (data.isEnd) {
            outRect.bottom = 20
        } else {
            outRect.bottom = 0
        }
    }
}
