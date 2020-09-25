package com.baozi.demo.fragment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.baozi.demo.R
import com.baozi.demo.fragment.SimpleRecyclerViewFg
import com.baozi.demo.item.news.NewsItemBean
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.adpater.TreeRecyclerType
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.TreeItem

import java.util.ArrayList
import java.util.Random

/**
 * @author jlanglang  2017/7/5 10:22
 * @版本 2017-8-19;有点勉强,不建议这样使用- -
 */

class NewsFg : SimpleRecyclerViewFg<TreeRecyclerAdapter>() {
    override var adapter = TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView!!.layoutManager = GridLayoutManager(context, 6)
        recyclerView!!.adapter = adapter
        val list = ArrayList<NewsItemBean>()
        for (i in 0..4) {
            val newsItemBean = NewsItemBean()
            newsItemBean.title = "123"
            newsItemBean.images = Random().nextInt(9) + 1
            list.add(newsItemBean)
        }
        val itemList = ItemHelperFactory.createItems(list)
        adapter.itemManager.replaceAllItem(itemList)
    }
}
