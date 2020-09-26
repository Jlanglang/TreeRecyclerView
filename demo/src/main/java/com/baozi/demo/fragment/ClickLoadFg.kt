package com.baozi.demo.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.baozi.demo.R
import com.baozi.demo.item.clickload.ClickLoadGroupItem
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.TreeItem
import kotlinx.android.synthetic.main.layout_rv_content.*

import java.util.Arrays

/**
 * 点击懒加载的demo
 */
class ClickLoadFg : Fragment() {
    private val adapter = TreeRecyclerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_rv_content, container, false)
        view.setBackgroundColor(Color.GRAY)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //初始化recyclerView
        rv_content.layoutManager = GridLayoutManager(rv_content.context, 4)
        rv_content.adapter = adapter
        val treeItem = ItemHelperFactory.createItem(arrayOf<String>(), ClickLoadGroupItem::class.java, null)
        adapter.itemManager.replaceAllItem(arrayListOf(treeItem).mapNotNull { it })
        adapter.setOnItemClickListener { _, position ->
            val item = adapter.getData(position)
            if (item?.data == null) {
                return@setOnItemClickListener
            }
            if (item is ClickLoadGroupItem) {
                if (item.child.isNullOrEmpty()) {
                    item.data = arrayOf("1", "2", "3")
                    Toast.makeText(rv_content.context, "第一次点击加载", Toast.LENGTH_SHORT).show()
                }
                item.isExpand = !item.isExpand
            }
        }
    }
}
