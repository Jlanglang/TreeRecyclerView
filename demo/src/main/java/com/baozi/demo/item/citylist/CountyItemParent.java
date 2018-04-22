package com.baozi.demo.item.citylist;

import com.baozi.demo.R;
import com.baozi.demo.bean.CityBean;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.util.List;

/**
 */
public class CountyItemParent extends TreeItemGroup<CityBean.CitysBean> {

    @Override
    public List<TreeItem> initChildList(CityBean.CitysBean data) {
        return ItemHelperFactory.createTreeItemList(data.getAreas(), AreaItem.class, this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_two;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, getData().getCityName());
    }
}
