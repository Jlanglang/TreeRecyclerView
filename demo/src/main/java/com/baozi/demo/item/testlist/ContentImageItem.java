package com.baozi.demo.item.testlist;

import com.baozi.demo.R;
import com.baozi.demo.bean.ContentBean;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

/**
 */

public class ContentImageItem extends TreeItem<ContentBean> {
    @Override
    public int getLayoutId() {
        return R.layout.testlist_content_image;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        viewHolder.setBackgroundRes(R.id.iv_content, R.mipmap.ic_launcher);
    }
}
