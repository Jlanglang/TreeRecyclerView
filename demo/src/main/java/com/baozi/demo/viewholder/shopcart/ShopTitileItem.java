package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.modle.ItemData;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.ViewHolder;
import com.baozi.treerecyclerview.view.BaseItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;
import com.baozi.treerecyclerview.helper.ItemHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class ShopTitileItem extends TreeItemGroup<StoreBean> {

    private List<BaseItem> selectItems;
    private boolean isCheck;

    @Override
    protected List<? extends BaseItem> initChildsList(StoreBean data) {
        return ItemHelper.createTreeItemList(data.getShopListBeen(), ContentItem.class, this);
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
    public void onClick() {
        isCheck = !isCheck;
        data.setCheck(isCheck);
        List<BaseItem> selectItems = getSelectItems();
        if (isCheck) {
            selectItems.clear();
            selectItems.addAll(getChilds());
        } else {
            selectItems.clear();
        }
        int size = childs.size();
        for (int i = 0; i < size; i++) {
            ShopListBean data = (ShopListBean) childs.get(i).getData();
            data.setCheck(isCheck);
        }
        getTreeItemManager().notifyDataSetChanged();
    }

    @Override
    public boolean onInterceptClick(TreeItem child) {
        List<BaseItem> selectItems = getSelectItems();
        if (selectItems.indexOf(child) == -1) {//不存在则删除
            selectItems.add(child);
        } else {//存在则添加
            selectItems.remove(child);
        }
        isCheck = !selectItems.isEmpty();
        data.setCheck(isCheck);
        return super.onInterceptClick(child);
    }


    public List<BaseItem> getSelectItems() {
        if (selectItems == null) {
            selectItems = new ArrayList<>();
        }
        return selectItems;
    }

    public void setSelectItems(List<BaseItem> selectItems) {
        this.selectItems = selectItems;
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }
}
