
package com.baozi.demo.moudle.shoplist;

import com.baozi.demo.R;
import com.baozi.demo.moudle.shoplist.bean.ShopListBean;
import com.baozi.demo.moudle.shoplist.bean.StoreBean;
import com.baozi.demo.moudle.shoptablist.TabItem;
import com.baozi.demo.moudle.shoptablist.bean.ShopTabBean;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSelectItemGroup;
import com.baozi.treerecyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class ShopGroupItem extends TreeSelectItemGroup<StoreBean> {

    private TabItem mHeadItem;

    @Override
    protected List<TreeItem> initChildList(StoreBean data) {
        List<TreeItem> treeItemList = ItemHelperFactory.createTreeItemList(data.getShopListBeen(), ContentItem.class, this);
        mHeadItem = new TabItem();
        ShopTabBean shopListBean = new ShopTabBean();
        shopListBean.setName("头部");
        mHeadItem.setData(shopListBean);
        treeItemList.add(0, mHeadItem);
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
        return child != mHeadItem && super.onInterceptClick(child);
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        if (!isChildCheck()) {
            getSelectItems().clear();
            if (getChild() == null) return;
            getSelectItems().addAll(getChild());
            getSelectItems().remove(mHeadItem);
        } else {
            getSelectItems().clear();
        }
        int size = getChild().size();
        for (int i = 0; i < size; i++) {
            Object data = getChild().get(i).getData();
            if (data != mHeadItem.getData()) {
                ((ShopListBean) data).setCheck(isChildCheck());
            }
        }
        getItemManager().notifyDataChanged();
    }


    @Override
    public SelectFlag selectFlag() {
        return SelectFlag.MULTIPLE_CHOICE;
    }

}
