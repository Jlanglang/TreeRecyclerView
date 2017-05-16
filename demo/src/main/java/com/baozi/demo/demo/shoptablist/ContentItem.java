package com.baozi.demo.demo.shoptablist;

import com.baozi.demo.R;
import com.baozi.demo.demo.shoptablist.bean.ShopTabContentBean;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItem;

/**
 * Created by baozi on 2017/5/10.
 */

public class ContentItem extends TreeItem<ShopTabContentBean> {
    @Override
    protected int initLayoutId() {
        return R.layout.item_shoptablist_content;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        viewHolder.setText(R.id.tv_content_name, data.getTitle());
        viewHolder.setText(R.id.tv_content_title, data.getName());
    }
}
