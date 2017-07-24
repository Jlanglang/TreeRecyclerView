package com.baozi.demo.moudle.shoptablist;

import com.baozi.demo.R;
import com.baozi.demo.moudle.shoptablist.bean.ShopTabContentBean;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

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
