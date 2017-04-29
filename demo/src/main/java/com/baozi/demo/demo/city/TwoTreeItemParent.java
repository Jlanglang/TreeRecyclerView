package com.baozi.demo.demo.city;

import com.baozi.demo.R;
import com.baozi.demo.demo.city.bean.CityBean;
import com.baozi.treerecyclerview.helper.ItemHelper;
import com.baozi.treerecyclerview.view.ViewHolder;
import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;

import java.util.List;

/**
 */
public class TwoTreeItemParent extends TreeItemGroup<CityBean.CitysBean> {

    @Override
    public List<? extends BaseItem> initChildsList(CityBean.CitysBean data) {
        return ItemHelper.createItemList(data.getAreas(), ThreeItem.class);
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
