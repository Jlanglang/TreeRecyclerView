package com.baozi.demo.item.testlist;

import com.baozi.demo.R;
import com.baozi.demo.bean.TitleBean;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.util.List;

/**
 * Created by baozi on 2017/4/27.
 */

public class ContentGroupItem extends TreeItemGroup<TitleBean> {
    @Override
    protected List<TreeItem> initChildList(TitleBean data) {
        return ItemHelperFactory.createItems(data.getSingleBeen(), this);
    }

    @Override
    public int getLayoutId() {
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
