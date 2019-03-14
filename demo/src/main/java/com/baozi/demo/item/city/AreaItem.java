package com.baozi.demo.item.city;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.baozi.demo.R;
import com.baozi.demo.bean.ProvinceBean;
import com.baozi.demo.item.cart.CartGroupItem;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class AreaItem extends TreeItem<ProvinceBean.CityBean.AreasBean> {

    @Override
    public int getLayoutId() {
        return R.layout.item_three;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getAreaName());
    }

    @Override
    public int getSpanSize(int maxSpan) {
        return maxSpan / 6;
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        super.onClick(viewHolder);
    }
}
