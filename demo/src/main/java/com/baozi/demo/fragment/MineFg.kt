package com.baozi.demo.fragment

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.baozi.demo.R
import com.baozi.demo.item.mine.MineCategoryBean
import com.baozi.demo.item.mine.MineItem
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.SimpleTreeItem

import java.util.Arrays

//RecyclerView实现首页我的页面
class MineFg : Fragment() {

    private var view: RecyclerView? = null
    private val adapter = TreeRecyclerAdapter()
    private val heads = Arrays.asList(
            MineCategoryBean("公开文章", null, "39", true),
            MineCategoryBean("关注", null, "57", true),
            MineCategoryBean("粉丝", null, "501", true),
            MineCategoryBean("每日签到", "1", null, true)
    )
    private val category = Arrays.asList(
            MineCategoryBean("私密文章", "1", null),
            MineCategoryBean("我的收藏", "1", null),
            MineCategoryBean("我的喜欢", "1", null),
            MineCategoryBean("已购内容", "1", null),
            MineCategoryBean("我的专题", "1", null, true),
            MineCategoryBean("我的文集", "1", null, true),
            MineCategoryBean("关注内容", "1", null, true),
            MineCategoryBean("我的钱包", "1", null, true)
    )
    private val item = Arrays.asList("条目1", "条目2", "条目3", "条目4", "条目5", "条目6")

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
        adapter.itemManager.clean()
        //添加头部
        adapter.itemManager.addItem(
                SimpleTreeItem(R.layout.item_mine_head).apply {
                    treeOffset = Rect(0, 0, 0, 2)
                }
        )
        //添加头部分类item
        adapter.itemManager.addItems(ItemHelperFactory.createItems(heads))
        //添加分类item
        adapter.itemManager.addItems(ItemHelperFactory.createItems(category))
        //添加横条item
        adapter.itemManager.addItems(ItemHelperFactory.createItems(item, MineItem::class.java))
    }
}
