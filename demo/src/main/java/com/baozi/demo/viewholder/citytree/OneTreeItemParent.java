package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.BaseItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;
import com.baozi.treerecyclerview.helper.ItemHelper;

import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class OneTreeItemParent extends TreeItemGroup<CityBean> {
    @Override
    public List<? extends BaseItem> initChildsList(CityBean data) {
        return ItemHelper.createTreeItemList(data.getCitys(), TwoTreeItemParent.class, this);
    }

    @Override
    public int initLayoutId() {
        return R.layout.itme_one;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getProvinceName());
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }
}
