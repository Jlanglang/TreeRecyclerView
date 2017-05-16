package com.baozi.demo.demo.citylist;

import com.baozi.demo.R;
import com.baozi.demo.demo.citylist.bean.CityBean;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItem;

/**
 */
public class ThreeItem extends TreeItem<CityBean.CitysBean.AreasBean> {


    @Override
    public int initLayoutId() {
        return R.layout.item_three;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getAreaName());
    }
}
