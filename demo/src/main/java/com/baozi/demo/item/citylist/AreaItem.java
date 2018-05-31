package com.baozi.demo.item.citylist;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.baozi.demo.R;
import com.baozi.demo.bean.CityBean;
import com.baozi.treerecyclerview.BindItemType;
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

    @Override
    public void getItemOffsets(@NonNull Rect outRect, RecyclerView.LayoutParams layoutParams, int position) {
        super.getItemOffsets(outRect, layoutParams, position);

    }

    @Override
    public int getSpanSize() {
        return 1;
    }

}
