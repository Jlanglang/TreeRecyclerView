package com.baozi.demo.demo.citylist;

import com.baozi.demo.R;
import com.baozi.demo.demo.citylist.bean.CityBean;
import com.baozi.treerecyclerview.factory.ItemFactory;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;

import java.util.List;

/**
 */
public class TwoTreeItemParent extends TreeItemGroup<CityBean.CitysBean> {

    @Override
    public List<? extends BaseItem> initChildsList(CityBean.CitysBean data) {
        return ItemFactory.createItemList(data.getAreas(), ThreeItem.class);
    }


    @Override
    public int initLayoutId() {
        return R.layout.item_two;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, getData().getCityName());
    }

    @Override
    public boolean isCanChangeExpand() {
        return data.getCityName().equals("朝阳区");
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }
}
