package com.baozi.demo.demo.shop;

import com.baozi.demo.R;
import com.baozi.demo.demo.shop.bean.ShopListBean;
import com.baozi.treerecyclerview.view.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItem;

/**
 * Created by baozi on 2016/12/22.
 */

public class ContentItem extends TreeItem<ShopListBean> {

    @Override
    protected int initLayoutId() {
        return R.layout.item_shopcart_content;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        viewHolder.setChecked(R.id.cb_ischeck, getData().isCheck());
    }

    @Override
    public void onClick() {
        getData().setCheck(!getData().isCheck());
        getTreeItemManager().notifyDataSetChanged();
    }
}
