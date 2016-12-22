package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.viewholder.TreeAdapterItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class TitleItem extends TreeAdapterItem<StoreBean> {

    public TitleItem(StoreBean data) {
        super(data);
    }

    @Override
    protected List<TreeAdapterItem> initChildsList(StoreBean data) {
        List<ShopListBean> shopListBeen = data.getShopListBeen();
        ArrayList<TreeAdapterItem> contentItems = new ArrayList<>();
        for (int i = 0; i < shopListBeen.size(); i++) {
            ShopListBean shopListBean = shopListBeen.get(i);
            ContentItem contentItem = new ContentItem(shopListBean);
            contentItems.add(contentItem);
        }
        return contentItems;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_shopcart_title;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, getData().isCheck());
    }
}
