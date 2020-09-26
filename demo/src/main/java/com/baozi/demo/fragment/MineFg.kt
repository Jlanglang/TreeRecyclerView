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
import kotlinx.android.synthetic.main.layout_rv_content.*

import java.util.Arrays

//RecyclerView实现首页我的页面
class MineFg : Fragment() {
    private val adapter = TreeRecyclerAdapter()
    private val heads = listOf(
            MineCategoryBean("公开文章", null, "39", true),
            MineCategoryBean("关注", null, "57", true),
            MineCategoryBean("粉丝", null, "501", true),
            MineCategoryBean("每日签到", "1", null, true)
    )
    private val category = listOf(
            MineCategoryBean("私密文章", "1", null),
            MineCategoryBean("我的收藏", "1", null),
            MineCategoryBean("我的喜欢", "1", null),
            MineCategoryBean("已购内容", "1", null),
            MineCategoryBean("我的专题", "1", null, true),
            MineCategoryBean("我的文集", "1", null, true),
            MineCategoryBean("关注内容", "1", null, true),
            MineCategoryBean("我的钱包", "1", null, true)
    )
    private val item = listOf("条目1", "条目2", "条目3", "条目4", "条目5", "条目6")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_rv_content, container, false)
        view.setBackgroundColor(Color.GRAY)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //初始化recyclerView
        rv_content.layoutManager = GridLayoutManager(view!!.context, 4)
        rv_content.adapter = adapter
        adapter.itemManager.apply {
            clean()
            //添加头部
            addItem(
                    SimpleTreeItem(R.layout.item_mine_head).apply {
                        treeOffset = Rect(0, 0, 0, 2)
                    }
            )
            //添加头部分类item
            addItems(ItemHelperFactory.createItems(heads))
            //添加分类item
            addItems(ItemHelperFactory.createItems(category))
            //添加横条item
            addItems(ItemHelperFactory.createItems(item, MineItem::class.java))
        }
    }
}
