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

public class TitletItem extends TreeParentItem<StoreBean> {

    private List<ShopListBean> mShopListBeen;

    @Override
    protected List<TreeItem> initChildsList(StoreBean data) {
        mShopListBeen = data.getShopListBeen();
        ArrayList<TreeItem> contentItems = new ArrayList<>();
        for (int i = 0; i < mShopListBeen.size(); i++) {
            ContentItem contentItem = new ContentItem();
            contentItem.setData(mShopListBeen.get(i));
            contentItem.setParentItem(this);
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

    @Override
    public void onClickChange(TreeItem treeItem) {
        boolean check = data.isCheck();
        data.setCheck(!check);
        for (int i = 0; i < mShopListBeen.size(); i++) {
            mShopListBeen.get(i).setCheck(!check);
        }
    }

    @Override
    public void onUpdate() {
        data.setCheck(false);
        for (int i = 0; i < mShopListBeen.size(); i++) {
            if (mShopListBeen.get(i).isCheck()) {
                data.setCheck(true);
            }
        }
    }
}
