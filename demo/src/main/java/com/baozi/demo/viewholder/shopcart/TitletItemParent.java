package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.BaseItem;
import com.baozi.treerecyclerview.view.ItemGroup;
import com.baozi.treerecyclerview.helper.ItemHelper;

import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class TitletItemParent extends ItemGroup<StoreBean> {

    private List<ShopListBean> mShopListBeen;

    @Override
    protected List<? extends BaseItem> initChildsList(StoreBean data) {
        mShopListBeen = data.getShopListBeen();
        return ItemHelper.createItemListForClass(data.getShopListBeen(), ContentItem.class, this);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_shopcart_title;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, getData().isCheck());
    }

//    @Override
//    public void onClickChange() {
//        boolean check = data.isCheck();
//        data.setCheck(!check);
//        for (int i = 0; i < mShopListBeen.size(); i++) {
//            mShopListBeen.get(i).setCheck(!check);
//        }
//    }

    @Override
    public void onClick(int position) {
        boolean check = data.isCheck();
        data.setCheck(!check);
        for (int i = 0; i < mShopListBeen.size(); i++) {
            mShopListBeen.get(i).setCheck(!check);
        }
    }

    @Override
    public void onUpdate() {
        data.setCheck(false);
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }
}
