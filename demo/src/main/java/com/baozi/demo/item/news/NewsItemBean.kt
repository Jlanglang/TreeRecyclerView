package com.baozi.demo.item.news

import com.baozi.treerecyclerview.annotation.TreeDataType

/**
 * @author jlanglang  2017/7/5 17:34
 * @版本 2.0
 * @Change
 */
@TreeDataType(iClass = NewsItem::class)
class NewsItemBean {
    var title: String? = null
    var images: Int = 0
}
