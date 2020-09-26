package com.baozi.demo.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.baozi.demo.R
import com.baozi.demo.item.clickload.ClickLoadGroupItem
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.TreeItem

import java.util.Arrays

/**
 * 点击懒加载的demo
 */
class ClickLoadFg : Fragment() {
    internal var view: RecyclerView? = null
    private val adapter = TreeRecyclerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (view == null) {
            view = inflater.inflate(R.layout.layout_rv_content, container, false) as RecyclerView
            view!!.setBackgroundColor(Color.GRAY)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //初始化recyclerView
        view!!.layoutManager = GridLayoutManager(view!!.context, 4)
        view!!.adapter = adapter
        val treeItem = ItemHelperFactory.createItem(arrayOf<String>(), ClickLoadGroupItem::class.java, null)
        adapter.itemManager.replaceAllItem(arrayListOf(treeItem).mapNotNull { it })
        adapter.setOnItemClickListener { _, position ->
            val item = adapter.getData(position)
            if (item == null || item.data == null) {
                return@setOnItemClickListener
            }
            if (item is ClickLoadGroupItem) {
                val clickLoadGroupItem = item as ClickLoadGroupItem?
                if (clickLoadGroupItem!!.child == null || clickLoadGroupItem.child!!.isEmpty()) {
                    clickLoadGroupItem.data = arrayOf("1", "2", "3")
                    clickLoadGroupItem.isExpand=true
                }
            }
        }
    }
}
