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
import kotlinx.android.synthetic.main.layout_rv_content.*

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.ref.WeakReference
import java.util.ArrayList

/**
 * 城市列表
 */
class CityAt : AppCompatActivity() {
    //根据item的状态展示,可折叠
    var treeRecyclerAdapter = TreeRecyclerAdapter(TreeRecyclerType.SHOW_EXPAND)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_rv_content)
        rv_content?.apply {
            layoutManager = GridLayoutManager(context, 6)
            itemAnimator = DefaultItemAnimator()
            adapter = treeRecyclerAdapter
        }
        treeRecyclerAdapter.itemManager.isOpenAnim = true//优化掉
        Thread {
            val string = getFromAssets("city.txt")
            Log.i("json", string)
            val cityBeen = JSON.parseArray(string, ProvinceBean::class.java)
            refresh(cityBeen)
        }.start()
    }

    private fun getFromAssets(fileName: String): String {
        val result = StringBuilder()
        try {
            val inputReader = InputStreamReader(resources.assets.open(fileName))
            return inputReader.readText()
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
                treeItem.isExpand = false
            }
            //添加到adapter
            treeRecyclerAdapter.itemManager.replaceAllItem(items)
        }
    }
}
