package com.baozi.demo.activity

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Pair
import android.view.View

import com.baozi.demo.R
import com.baozi.demo.fragment.ClickLoadFg
import com.baozi.demo.fragment.MineFg
import com.baozi.demo.fragment.NewsFg
import com.baozi.demo.item.cart.CartGroupItem
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.factory.ItemConfig
import com.baozi.treerecyclerview.item.SimpleTreeItem
import com.baozi.treerecyclerview.item.TreeItem

import java.util.ArrayList

/**
 * @author jlanglang  2016/12/22 9:58
 * @版本 2.0
 * @Change
 */
class MainActivity : AppCompatActivity() {
    //数据集合
    private val itemPairs = arrayOf<Pair<*, *>>(Pair("三级城市", CityAt::class.java), Pair("购物车", CartAt::class.java), Pair("新闻", NewsFg::class.java), Pair("索引", SortAt::class.java), Pair("索引加侧滑删除", SwipeSortAt::class.java), Pair("个人中心", MineFg::class.java), Pair("淘宝首页", TBActivity::class.java), Pair("点击懒加载", ClickLoadFg::class.java), Pair("画廊", TestActivity::class.java))
    private val adapter = TreeRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_rv_content)
        initRecyclerVIew()
        initData()
        ItemConfig.register(CartGroupItem::class.java)

    }

    /**
     * 简单创建Item
     */
    private fun initData() {
        val items = ArrayList<TreeItem<*>>()
        for (itemPair in itemPairs) {
            val simpleTreeItem = SimpleTreeItem(R.layout.item_mine)
                    .apply {
                        itemBind = { viewHolder ->
                            val itemPair1 = itemPairs[viewHolder.layoutPosition]
                            viewHolder.setText(R.id.tv_name, itemPair1.first as String)
                        }
                        itemClick = { viewHolder ->
                            val itemPair12 = itemPairs[viewHolder.layoutPosition]
                            val aClass = itemPair12.second as Class<*>
                            val isFragment = Fragment::class.java.isAssignableFrom(aClass)//判断是不是fragment的子类
                            if (isFragment) {
                                startFragment(itemPair12.second as Class<Fragment>)
                            } else {
                                startAt(itemPair12.second as Class<*>)
                            }
                        }
                    }
            simpleTreeItem.data = itemPair
            items.add(simpleTreeItem)
        }
        adapter.itemManager.replaceAllItem(items)
    }

    /**
     * 初始化列表
     */
    private fun initRecyclerVIew() {
        val rv_content = findViewById<RecyclerView>(R.id.rv_content)
        rv_content.layoutManager = LinearLayoutManager(this)
        rv_content.adapter = adapter
        rv_content.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = 10
            }
        })
    }

    fun startAt(zClass: Class<*>) {
        val intent = Intent(this, zClass)
        startActivity(intent)
    }

    fun startFragment(zClass: Class<Fragment>) {
        try {
            val fragment = zClass.newInstance()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(android.R.id.content, fragment, null)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commitAllowingStateLoss()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }
}

