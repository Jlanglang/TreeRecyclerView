package com.baozi.demo.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView

import com.baozi.demo.R
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.item.SimpleTreeItem
import com.baozi.treerecyclerview.item.TreeItem

import java.util.ArrayList

class GalleryFg : SimpleRecyclerViewFg<TreeRecyclerAdapter>() {
    private val snapHelper = LinearSnapHelper()
    internal lateinit var linearLayoutManager: LinearLayoutManager

    val items: List<TreeItem<*>>
        get() {
            val list = ArrayList<TreeItem<*>>()
            for (i in 0..19) {
                list.add(
                        SimpleTreeItem(R.layout.item_grallery)
                                .apply {
                                    treeOffset = Rect(20, 20, 20, 20)
                                    itemBind = { viewHolder ->
                                        val itemView = viewHolder.itemView as TextView
                                        itemView.text = viewHolder.layoutPosition.toString() + ""
                                        viewHolder.itemView.scaleY = 1f
                                    }
                                }
                )
            }
            return list
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        linearLayoutManager = LinearLayoutManager(context, 0, false)
        recyclerView!!.layoutManager = linearLayoutManager
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scaleItem()
            }
        })
        adapter.itemManager.replaceAllItem(items)
    }

    fun scaleItem() {
        val first = linearLayoutManager.findFirstVisibleItemPosition()
        val last = linearLayoutManager.findLastVisibleItemPosition()
        val center = first + (last - first) / 2
        val range = last - first
        Log.i("position", "$first:$last:$center:$range")
        for (i in 0 until range) {
            val item = recyclerView!!.findViewHolderForLayoutPosition(first + i) ?: return
            if (first + i == center) {
                item.itemView.scaleY = 1.2f
                continue
            }
            item.itemView.scaleY = 1f
        }
    }
}
