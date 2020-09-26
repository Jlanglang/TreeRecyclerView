package com.baozi.demo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.CheckBox
import android.widget.TextView

import com.baozi.demo.R
import com.baozi.demo.item.cart.CartBean
import com.baozi.demo.item.cart.CartItem
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.TreeSelectItemGroup
import kotlinx.android.synthetic.main.activity_shopping_cart.*

import java.util.ArrayList

/**
 * Created by a123 on 2018/6/5.
 * 购物车列表
 */
class CartAt : AppCompatActivity() {
    private val adapter = TreeRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)
        rv_content.layoutManager = LinearLayoutManager(this)
        rv_content.adapter = adapter
        val beans = ArrayList<CartBean>()
        beans.add(CartBean(3))

        val groupItem = ItemHelperFactory.createItems(beans)

        adapter.itemManager.replaceAllItem(groupItem)
        adapter.setOnItemClickListener { viewHolder, position ->
            //因为外部和内部会冲突
            val item = adapter.getData(position)
            item?.onClick(viewHolder)
            notifyPrice()
        }
        initView()
        notifyPrice()
    }

    /**
     * 更新价格
     */
    fun notifyPrice() {
        var isSelectAll = true//默认全选
        var price = 0
        for (item in adapter.getData()) {
            if (item is TreeSelectItemGroup<*>) {
                if (!item.isSelect) {//是否有选择的子类
                    //有一个没选则不是全选
                    isSelectAll = false
                    continue
                }
                if (!item.isSelectAll) {//是否全选了子类
                    //有一个没选则不是全选
                    isSelectAll = false
                }
                val selectItems = item.selectItems
                for (child in selectItems) {
                    if (child is CartItem) {
                        val data = child.data as Int
                        price += data
                    }
                }
            }
        }
        adapter.notifyDataSetChanged()

        (findViewById<TextView>(R.id.tv_all_price)).text = "￥$price"
        val checkBox = findViewById<CheckBox>(R.id.cb_all_check)
        checkBox.isChecked = isSelectAll
    }

    fun initView() {
        val checkBox = findViewById<CheckBox>(R.id.cb_all_check)
        checkBox.setOnClickListener { v ->
            for (item in adapter.getData()) {
                if (item is TreeSelectItemGroup<*>) {
                    item.selectAll((v as CheckBox).isChecked)
                }
            }
            notifyPrice()
        }
    }

}
