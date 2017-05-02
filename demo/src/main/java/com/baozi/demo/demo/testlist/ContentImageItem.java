package com.baozi.demo.demo.testlist;

import com.baozi.demo.R;
import com.baozi.demo.demo.testlist.bean.ContentBean;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItem;

/**
 * Created by baozi on 2017/4/27.
 */

public class ContentImageItem extends TreeItem<ContentBean> {
    @Override
    protected int initLayoutId() {
        return R.layout.testlist_content_image;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        viewHolder.setBackgroundRes(R.id.iv_content, R.mipmap.ic_launcher);
    }
}
