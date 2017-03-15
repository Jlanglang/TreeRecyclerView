package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.viewholder.TreeItem;

/**
 * Created by baozi on 2016/12/22.
 */

public class ShopItem extends TreeItem<ShopListBean> {


    public ShopItem(ShopListBean data) {
        super(data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shopcart_content;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, getData().isCheck());
    }

    @Override
    public void onClick() {
        getData().setCheck(!getData().isCheck());
        parentItem.updateView();
    }
}
