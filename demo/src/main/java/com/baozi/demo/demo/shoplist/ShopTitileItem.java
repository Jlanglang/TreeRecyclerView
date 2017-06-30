
package com.baozi.demo.demo.shoplist;

import com.baozi.demo.R;
import com.baozi.demo.demo.shoplist.bean.ShopListBean;
import com.baozi.demo.demo.shoplist.bean.StoreBean;
import com.baozi.demo.demo.shoptablist.bean.ShopTabContentBean;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeSelectItemGroup;
import com.baozi.treerecyclerview.adpater.ViewHolder;

import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class ShopTitileItem extends TreeSelectItemGroup<StoreBean> {

    private com.baozi.demo.demo.shoptablist.ContentItem mContentItem;

    @Override
    protected List<TreeItem> initChildsList(StoreBean data) {
        List<TreeItem> treeItemList = ItemHelperFactory.createTreeItemList(data.getShopListBeen(), ContentItem.class, this);
        mContentItem = new com.baozi.demo.demo.shoptablist.ContentItem();
        ShopTabContentBean shopListBean = new ShopTabContentBean();
        shopListBean.setName("头部");
        shopListBean.setTitle("头部");
        mContentItem.setData(shopListBean);
        treeItemList.add(0, mContentItem);
        return treeItemList;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_shoplist_title;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, isChildCheck());
    }

    @Override
    public boolean onInterceptClick(TreeItem child) {
        return child != mContentItem && super.onInterceptClick(child);
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

}
