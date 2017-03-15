package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.viewholder.TreeParentItem;

import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class StoreItem extends TreeParentItem<StoreBean> {


    private List<ShopListBean> mShopListBeen;

    public StoreItem(StoreBean data) {
        super(data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shopcart_title;
    }

    @Override
    protected void initChildsList(StoreBean data) {
        mShopListBeen = data.getShopListBeen();
        for (int i = 0; i < mShopListBeen.size(); i++) {
            ShopItem contentItem = new ShopItem(mShopListBeen.get(i));
            addView(contentItem);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, getData().isCheck());
    }

    public void onClick() {
        boolean check = data.isCheck();
        data.setCheck(!check);
        for (int i = 0; i < mShopListBeen.size(); i++) {
            mShopListBeen.get(i).setCheck(!check);
        }
        updateView();
    }

    @Override
    public void updateView() {
        data.setCheck(false);
        for (int i = 0; i < mShopListBeen.size(); i++) {
            if (mShopListBeen.get(i).isCheck()) {
                data.setCheck(true);
            }
        }
        super.updateView();
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }

}
