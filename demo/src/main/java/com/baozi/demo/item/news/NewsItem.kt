package com.baozi.demo.item.news

import android.graphics.Rect

import com.baozi.demo.R
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.SimpleTreeItem
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.item.TreeItemGroup

import java.util.ArrayList

/**
 * @author jlanglang  2017/7/5 17:15
 * @版本 2.0
 * @Change
 */
//新闻列表,一级父
class NewsItem : TreeItemGroup<NewsItemBean>() {
    override fun initChild(data: NewsItemBean): List<TreeItem<*>>? {
        //添加图片
        val images = data.images
        val child = ArrayList<String>()
        for (i in 0 until images) {
            child.add("")
        }
        val treeItemList = ItemHelperFactory.createItems(child, NewsImageItem::class.java, this)

        //添加尾部
        treeItemList.add(SimpleTreeItem(R.layout.item_news_foot)
                .onItemClick { viewHolder ->
                    //点击跳转
                }.setTreeOffset(Rect(0, 0, 0, 20)))
        return treeItemList
    }

    override fun getLayoutId(): Int {
        return R.layout.item_news_title
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {
        viewHolder.setText(R.id.tv_title, data.title)
    }

}
