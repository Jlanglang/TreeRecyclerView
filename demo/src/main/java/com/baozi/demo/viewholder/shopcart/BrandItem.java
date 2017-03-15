package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.viewholder.TreeParentItem;

import java.util.List;

/**
 * @author jlanglang  2017/3/15 15:36
 * @版本 2.0
 * @Change
 */

public class BrandItem extends TreeParentItem<BrandBean> {

    public BrandItem(BrandBean data) {
        super(data);
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_brand_title;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, data.isCheck());
    }

    @Override
    protected void initChildsList(BrandBean data) {
        List<StoreBean> storeBeens = data.getStoreBeens();
        for (int i = 0; i < storeBeens.size(); i++) {
            StoreItem contentItem = new StoreItem(storeBeens.get(i));
            addView(contentItem);
        }
    }

    @Override
    public void onClick() {
        boolean check = data.isCheck();
        data.setCheck(!check);
        for (int i = 0; i < data.getStoreBeens().size(); i++) {
            data.getStoreBeens().get(i).setCheck(!check);
        }
        updateView();
    }
}
