package com.baozi.demo.viewholder.shopcart;

import android.view.View;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.viewholder.TreeItem;
import com.baozi.treerecyclerview.viewholder.TreeParentItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

/**
 * Created by baozi on 2016/12/22.
 */

public class ContentItem extends TreeItem<ShopListBean> {


//    public ContentItem(ShopListBean data, TreeParentItem parentItem) {
//        super(data, parentItem);
//    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_shopcart_content;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, getData().isCheck());
    }

    @Override
    public void onClickChange(TreeItem treeItem) {
        getData().setCheck(!getData().isCheck());
        parentItem.onUpdate();
//        mTreeItemManager.notifyDataSetChanged();
    }
}
