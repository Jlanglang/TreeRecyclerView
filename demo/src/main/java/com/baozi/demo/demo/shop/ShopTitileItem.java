package com.baozi.demo.demo.shop;

import com.baozi.demo.R;
import com.baozi.demo.demo.shop.bean.ShopListBean;
import com.baozi.demo.demo.shop.bean.StoreBean;
import com.baozi.treerecyclerview.factory.ItemFactory;
import com.baozi.treerecyclerview.view.TreeSelectItemGroup;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.base.BaseItem;

import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class ShopTitileItem extends TreeSelectItemGroup<StoreBean> {

    @Override
    protected List<? extends BaseItem> initChildsList(StoreBean data) {
        return ItemFactory.createTreeItemList(data.getShopListBeen(), ContentItem.class, this);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_shopcart_title;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, isChildCheck());
    }

    @Override
    public void onClick() {
        if (!isChildCheck()) {
            getSelectItems().clear();
            getSelectItems().addAll(getChilds());
        } else {
            getSelectItems().clear();
        }
        int size = getChilds().size();
        for (int i = 0; i < size; i++) {
            ShopListBean data = (ShopListBean) getChilds().get(i).getData();
            data.setCheck(isChildCheck());
        }
        getItemManager().notifyDataChanged();
    }


    @Override
    public SelectFlag selectFlag() {
        return SelectFlag.MULTIPLE_CHOICE;
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }
}
