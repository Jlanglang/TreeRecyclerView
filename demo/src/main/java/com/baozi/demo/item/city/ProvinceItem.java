package com.baozi.demo.item.city;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItemGroup;
import com.baozi.treerecyclerview.manager.ItemManager;

import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class ProvinceItem extends TreeItemGroup<ProvinceBean> {

    @Override
    public List<TreeItem> initChild(ProvinceBean data) {
        List<TreeItem> items = ItemHelperFactory.createItems(data.citys, this);
        for (int i = 0; i < items.size(); i++) {
            TreeItemGroup treeItem = (TreeItemGroup) items.get(i);
            treeItem.setExpand(false);
        }
        return items;
    }

    @Override
    public int getLayoutId() {
        return R.layout.itme_one;
    }

    @Override
    public boolean isCanExpand() {
        return true;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder) {
        holder.setText(R.id.tv_content, data.provinceName);
    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, RecyclerView.LayoutParams layoutParams, int position) {
        super.getItemOffsets(outRect, layoutParams, position);
        outRect.bottom = 1;
    }
}
