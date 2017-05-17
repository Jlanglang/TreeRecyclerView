package com.baozi.demo.demo.testlist;

import com.baozi.demo.R;
import com.baozi.demo.demo.testlist.bean.TitleBean;
import com.baozi.treerecyclerview.factory.ItemFactory;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;

import java.util.List;

/**
 * Created by baozi on 2017/4/27.
 */

public class TitleItem extends TreeItemGroup<TitleBean> {
    @Override
    protected List<? extends BaseItem> initChildsList(TitleBean data) {
        return ItemFactory.createItemList(data.getSingleBeen());
    }

    @Override
    protected int initLayoutId() {
        return R.layout.testlist_title;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
    }

    @Override

    public boolean isCanChangeExpand() {
        return false;
    }

}
