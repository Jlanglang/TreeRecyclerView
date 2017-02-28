package com.baozi.demo.viewholder.shopcart;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.viewholder.TreeItem;
import com.baozi.treerecyclerview.viewholder.TreeParentItem;

import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class TitletItem extends TreeParentItem<StoreBean> {


    private List<ShopListBean> mShopListBeen;

    public TitletItem(StoreBean data) {
        super(data);
    }

    @Override
    protected void initChildsList(StoreBean data) {
        mShopListBeen = data.getShopListBeen();
//        ArrayList<TreeItem> contentItems = new ArrayList<>();
        for (int i = 0; i < mShopListBeen.size(); i++) {
            ContentItem contentItem = new ContentItem(mShopListBeen.get(i));
            addView(contentItem);
        }
//        return contentItems;
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
        mTreeItemManager.notifyDataSetChanged();
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
