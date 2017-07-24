package com.baozi.demo.moudle.newslist;

import com.baozi.demo.R;
import com.baozi.demo.moudle.newslist.bean.NewsItemBean;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

/**
 * @author jlanglang  2017/7/5 17:15
 * @版本 2.0
 * @Change
 */

public class NewsImageItem extends TreeItem<NewsItemBean.NewsImageBean> {
    @Override
    protected int initLayoutId() {
        return R.layout.item_news_image;
    }

    @Override
    public int getSpanSize() {
        return 2;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {

    }
}
