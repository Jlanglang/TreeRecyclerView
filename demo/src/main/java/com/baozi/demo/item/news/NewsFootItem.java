package com.baozi.demo.item.news;

import android.support.annotation.NonNull;

import com.baozi.demo.R;
import com.baozi.demo.bean.NewsItemBean;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

/**
 * @author jlanglang  2017/7/5 17:16
 * @版本 2.0
 * @Change
 */
@Deprecated
public class NewsFootItem extends TreeItem<NewsItemBean> {

    @Override
    public int getLayoutId() {
        return R.layout.item_news_foot;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {

    }
}
