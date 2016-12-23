package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.viewholder.TreeItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

/**
 * Created by baozi on 2016/12/22.
 */

public class ContentItem extends TreeItem<ShopListBean> {

    public ContentItem(ShopListBean data) {
        super(data);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_shopcart_content;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, getData().isCheck());
    }
}
