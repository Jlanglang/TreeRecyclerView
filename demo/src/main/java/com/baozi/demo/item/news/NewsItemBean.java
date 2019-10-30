package com.baozi.demo.item.news;

import com.baozi.demo.item.news.NewsImageItem;
import com.baozi.demo.item.news.NewsItem;
import com.baozi.treerecyclerview.annotation.TreeDataType;

/**
 * @author jlanglang  2017/7/5 17:34
 * @版本 2.0
 * @Change
 */
@TreeDataType(iClass = NewsItem.class)
public class NewsItemBean {
    public String title;
    public int images;
}
