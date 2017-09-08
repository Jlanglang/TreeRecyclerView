package com.baozi.demo.moudle.shoplist;

import com.baozi.demo.R;
import com.baozi.demo.moudle.shoplist.bean.ShopListBean;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

/**
 * Created by baozi on 2016/12/22.
 */

public class ContentItem extends TreeItem<ShopListBean> {

    @Override
    protected int initLayoutId() {
        return R.layout.item_shoplist_content;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        viewHolder.setChecked(R.id.cb_ischeck, getData().isCheck());
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        getData().setCheck(!getData().isCheck());
        getItemManager().notifyDataChanged();
    }
}
