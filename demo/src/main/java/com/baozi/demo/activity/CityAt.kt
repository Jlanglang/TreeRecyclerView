package com.baozi.demo.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver

import com.alibaba.fastjson.JSON
import com.baozi.demo.R
import com.baozi.demo.item.city.ProvinceBean
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.adpater.TreeRecyclerType
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.item.TreeItemGroup

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.ref.WeakReference
import java.util.ArrayList

/**
 * 城市列表
 */
class CityAt : AppCompatActivity() {
    //根据item的状态展示,可折叠
    internal var treeRecyclerAdapter = TreeRecyclerAdapter(TreeRecyclerType.SHOW_EXPAND)
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_rv_content)
        recyclerView = findViewById(R.id.rv_content)
        recyclerView!!.layoutManager = GridLayoutManager(this, 6)
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = treeRecyclerAdapter
        treeRecyclerAdapter.itemManager.isOpenAnim = true//优化掉
        object : Thread() {
            override fun run() {
                super.run()
                val string = getFromAssets("city.txt")
                Log.i("json", string)
                val cityBeen = JSON.parseArray(string, ProvinceBean::class.java)
                refresh(cityBeen)
            }
        }.start()

    }

    fun getFromAssets(fileName: String): String {
        val result = StringBuilder()
        try {
            val inputReader = InputStreamReader(resources.assets.open(fileName))
            val bufReader = BufferedReader(inputReader)
            bufReader.readLines().forEach { t ->
                result.append(t)
            }
            return result.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result.toString()
    }

    private fun refresh(cityBeen: List<ProvinceBean>) {
        runOnUiThread {
            //创建item
            //新的
            val items = ItemHelperFactory.createItems(cityBeen)
            for (i in items.indices) {
                val treeItem = items[i] as TreeItemGroup<*>
                treeItem.isExpand=false
            }
            //添加到adapter
            treeRecyclerAdapter.itemManager.replaceAllItem(items)
        }
    }

    fun getLastVisiblePosition(layoutManager: RecyclerView.LayoutManager): Int {
        if (layoutManager is LinearLayoutManager) {
            return layoutManager.findLastVisibleItemPosition()

        }

        if (layoutManager is GridLayoutManager) {
            return layoutManager.findLastVisibleItemPosition()

        }

        if (layoutManager is StaggeredGridLayoutManager) {
            val first = IntArray(layoutManager.spanCount)
            layoutManager.findLastVisibleItemPositions(first)

            val list = ArrayList<Int>(first.size)
            return if (list == null || list.size == 0) {
                -1
            } else list[list.size - 1]
        }

        return -1

    }

}
