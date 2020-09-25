package com.baozi.demo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView

import com.baozi.demo.R
import com.baozi.demo.item.sort.IndexBar
import com.baozi.demo.item.sort.SortGroupItem
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.widget.TreeSortAdapter

import java.util.ArrayList

/**
 * Created by baozi on 2017/8/19.
 * 侧滑+索引
 */

class SortAt : AppCompatActivity() {
    private val mTreeSortAdapter = TreeSortAdapter()
    private var mLinearLayoutManager: LinearLayoutManager? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort)
        val rv_content = findViewById<RecyclerView>(R.id.rv_content)
        val tv_index = findViewById<TextView>(R.id.tv_index)
        val qb_sort = findViewById<IndexBar>(R.id.qb_sort)
        qb_sort.setOnIndexChangedListener { letter ->
            val sortIndex = mTreeSortAdapter.getSortIndex(letter)
            mLinearLayoutManager!!.scrollToPositionWithOffset(sortIndex, 0)
        }
        qb_sort.setIndexs(LETTERS)
        qb_sort.setSelectedIndexTextView(tv_index)

        mLinearLayoutManager = LinearLayoutManager(this)
        rv_content.layoutManager = mLinearLayoutManager
        rv_content.adapter = mTreeSortAdapter
        initData()
    }


    private fun initData() {
        val groupItems = ArrayList<TreeItem<*>>()
        for (str in LETTERS) {
            val sortGroupItem = SortGroupItem()
            sortGroupItem.sortKey = str
            sortGroupItem.data = null
            groupItems.add(sortGroupItem)
        }
        mTreeSortAdapter.itemManager.replaceAllItem(groupItems)
    }

    companion object {
        private val LETTERS = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    }

}
