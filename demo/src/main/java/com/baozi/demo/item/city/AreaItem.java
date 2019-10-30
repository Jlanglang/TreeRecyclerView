package com.baozi.demo.item.city;

import android.support.annotation.NonNull;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

/**
 */
public class AreaItem extends TreeItem<ProvinceBean.CityBean.AreasBean> {

    @Override
    public int getLayoutId() {
        return R.layout.item_three;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder) {
        holder.setText(R.id.tv_content, data.areaName);
    }

    @Override
    public int getSpanSize(int maxSpan) {
        return maxSpan / 6;
    }
}
