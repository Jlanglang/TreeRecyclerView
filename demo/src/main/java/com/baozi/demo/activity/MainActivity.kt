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
import kotlinx.android.synthetic.main.layout_rv_content.*

import java.util.ArrayList

/**
 * @author jlanglang  2016/12/22 9:58
 * @版本 2.0
 * @Change
 */
class MainActivity : AppCompatActivity() {
    //数据集合
    private val itemPairs = mapOf<String, Class<*>>(
            "三级城市" to CityAt::class.java,
            "购物车" to CartAt::class.java,
            "新闻" to NewsFg::class.java,
            "索引" to SortAt::class.java,
            "索引加侧滑删除" to SwipeSortAt::class.java,
            "个人中心" to MineFg::class.java,
            "淘宝首页" to TBActivity::class.java,
            "点击懒加载" to ClickLoadFg::class.java,
            "画廊" to TestActivity::class.java)
    private val _adapter = TreeRecyclerAdapter()

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
                            val map = getCastData<Map.Entry<String, Class<*>>>()
                            viewHolder.setText(R.id.tv_name, map?.key)
                        }
                        itemClick = { _ ->
                            getCastData<Map.Entry<String, Class<*>>>()?.apply {
                                val isFragment = Fragment::class.java.isAssignableFrom(value)//判断是不是fragment的子类
                                if (isFragment) {
                                    startFragment(value as? Class<Fragment>)
                                } else {
                                    startAt(value)
                                }
                            }
                        }
                    }
            simpleTreeItem.data = itemPair
            items.add(simpleTreeItem)
        }
        _adapter.itemManager.replaceAllItem(items)
    }

    /**
     * 初始化列表
     */
    private fun initRecyclerVIew() {
        rv_content?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = _adapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)
                    outRect.top = 10
                }
            })
        }
    }

    fun startAt(zClass: Class<*>) {
        val intent = Intent(this, zClass)
        startActivity(intent)
    }

    fun startFragment(zClass: Class<out Fragment>?) {
        zClass ?: return
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

