package com.baozi.demo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.baozi.demo.R
import com.baozi.demo.item.sort.IndexBar
import com.baozi.demo.item.sort.SortGroupItem
import com.baozi.treerecyclerview.adpater.wrapper.HeaderWrapper
import com.baozi.treerecyclerview.adpater.wrapper.SwipeWrapper
import com.baozi.treerecyclerview.adpater.wrapper.TreeLoadWrapper
import com.baozi.treerecyclerview.item.SimpleTreeItem
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.widget.TreeSortAdapter
import kotlinx.android.synthetic.main.activity_sort.*

import java.util.ArrayList

/**
 * Created by baozi on 2017/8/19.
 * 在SortActivity的代码逻辑上,加少部分代码实现
 */

class SwipeSortAt : AppCompatActivity() {
    private var mTreeSortAdapter: TreeSortAdapter? = null
    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mWrapper: TreeLoadWrapper? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort)
        qb_sort.setOnIndexChangedListener { letter ->
            val sortIndex = mTreeSortAdapter!!.getSortIndex(letter)
            mLinearLayoutManager!!.scrollToPositionWithOffset(sortIndex, 0)
        }
        qb_sort.setIndexs(LETTERS)
        qb_sort.setSelectedIndexTextView(tv_index)

        mLinearLayoutManager = GridLayoutManager(this, 2)
        rv_content.layoutManager = mLinearLayoutManager
        //创建索引adapter
        mTreeSortAdapter = TreeSortAdapter()
        mTreeSortAdapter!!.itemManager.isOpenAnim = true
        val headerWrapper = HeaderWrapper(mTreeSortAdapter)
        addHeadView(headerWrapper)
        //包装成侧滑删除列表
        val adapter = SwipeWrapper(headerWrapper)
        mWrapper = TreeLoadWrapper(adapter)
        mWrapper?.setEmptyView(SimpleTreeItem(R.layout.layout_empty, spanSize = 0))
        mWrapper?.setLoadingView(R.layout.layout_loading)

        rv_content.adapter = mWrapper

        initData()
    }

    private fun addHeadView(headerWrapper: HeaderWrapper<*>) {
        for (i in 0..4) {
            //添加头部View1
            val headView = TextView(this)
            headView.text = "headView$i"
            headView.gravity = Gravity.CENTER
            headView.layoutParams = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320)
            headerWrapper.addHeaderView(headView)
        }
    }

    private fun initData() {
        val groupItems = ArrayList<TreeItem<*>>()
        for (i in LETTERS.indices) {
            val sortGroupItem = SortGroupItem()
            sortGroupItem.sortKey = LETTERS[i]
            sortGroupItem.data = i
            groupItems.add(sortGroupItem)
        }
        Thread(Runnable {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            runOnUiThread {
                mWrapper!!.setType(TreeLoadWrapper.Type.REFRESH_OVER)
                mWrapper!!.itemManager.notifyDataChanged()
            }
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            runOnUiThread { mWrapper!!.setType(TreeLoadWrapper.Type.LOADING) }
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            runOnUiThread {
                mWrapper!!.setType(TreeLoadWrapper.Type.REFRESH_OVER)
                mWrapper!!.itemManager!!.replaceAllItem(groupItems)
            }
        }).start()


    }

    companion object {
        private val LETTERS = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    }
}
