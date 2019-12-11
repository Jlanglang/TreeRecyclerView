package com.baozi.demo.item.city;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.util.List;

/**
 *
 */
public class CountyItem extends TreeItemGroup<ProvinceBean.CityBean> {

    @Override
    public List<TreeItem> initChild(ProvinceBean.CityBean data) {
        return ItemHelperFactory.createItems(data.areas, this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_two;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder) {
        holder.setText(R.id.tv_content, data.cityName);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, RecyclerView.LayoutParams layoutParams, int position) {
        super.getItemOffsets(outRect, layoutParams, position);
        outRect.top = 1;
    }
}
