package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.viewholder.TreeParentItem;
import com.baozi.treerecyclerview.viewholder.TreeItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class TitleTreeParentItem extends TreeParentItem<StoreBean> {

    public TitleTreeParentItem(StoreBean data) {
        super(data);
    }

    @Override
    protected List<TreeItem> initChildsList(StoreBean data) {
        List<ShopListBean> shopListBeen = data.getShopListBeen();
        ArrayList<TreeItem> contentItems = new ArrayList<>();
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
