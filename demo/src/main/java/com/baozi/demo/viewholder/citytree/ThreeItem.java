package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.viewholder.TreeItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

/**
 */
public class ThreeItem extends TreeItem<CityBean.CitysBean.AreasBean> {
    public ThreeItem(CityBean.CitysBean.AreasBean data) {
        super(data);
    }


    @Override
    public int initLayoutId() {
        return R.layout.item_three;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getAreaName());
    }
}
