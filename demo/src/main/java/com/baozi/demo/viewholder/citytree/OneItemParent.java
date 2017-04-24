package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.BaseItem;
import com.baozi.treerecyclerview.view.ItemGroup;
import com.baozi.treerecyclerview.helper.ItemHelper;

import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class OneItemParent extends ItemGroup<CityBean> {
    @Override
    public List<? extends BaseItem> initChildsList(CityBean data) {
        return ItemHelper.createItemListForClass(data.getCitys(), TwoItemParent.class, this);
    }

    @Override
    public int initLayoutId() {
        return R.layout.itme_one;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getProvinceName());
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }
}
