package com.baozi.demo.moudle.testlist;

import com.baozi.demo.R;
import com.baozi.demo.moudle.testlist.bean.TitleBean;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;

import java.util.List;

/**
 * Created by baozi on 2017/4/27.
 */

public class TitleItem extends TreeItemGroup<TitleBean> {
    @Override
    protected List<TreeItem> initChildsList(TitleBean data) {
        return ItemHelperFactory.createTreeItemList(data.getSingleBeen(), this);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.testlist_title;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
    }

    @Override

    public boolean isCanExpand() {
        return false;
    }

}
