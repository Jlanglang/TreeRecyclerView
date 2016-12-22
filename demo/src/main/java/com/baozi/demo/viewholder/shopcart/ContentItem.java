package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.viewholder.TreeAdapterItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class ContentItem extends TreeAdapterItem<ShopListBean> {

    public ContentItem(ShopListBean data) {
        super(data);
    }

    @Override
    protected List<TreeAdapterItem> initChildsList(ShopListBean data) {
        return null;
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
