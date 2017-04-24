package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.BaseItem;

/**
 * Created by baozi on 2016/12/22.
 */

public class ContentItem extends BaseItem<ShopListBean> {

    @Override
    protected int initLayoutId() {
        return R.layout.item_shopcart_content;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, getData().isCheck());
    }

    @Override
    public void onClick(int position) {
        getData().setCheck(!getData().isCheck());
        if (parentItem != null) {
            parentItem.onUpdate();
        }
    }
}
