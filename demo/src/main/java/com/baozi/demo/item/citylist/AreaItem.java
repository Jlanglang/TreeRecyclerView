package com.baozi.demo.item.citylist;

import com.baozi.demo.R;
import com.baozi.demo.bean.CityBean;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

/**
 */
public class AreaItem extends TreeItem<CityBean.CitysBean.AreasBean> {


    @Override
    public int getLayoutId() {
        return R.layout.item_three;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getAreaName());
    }
}
