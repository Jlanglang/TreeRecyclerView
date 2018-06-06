package com.baozi.demo.item.city;

import com.baozi.demo.R;
import com.baozi.demo.bean.ProvinceBean;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class ProvinceItemParent extends TreeItemGroup<ProvinceBean> {
    @Override
    public List<TreeItem> initChildList(ProvinceBean data) {
        return ItemHelperFactory.createItems(data.getCitys(), this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.itme_one;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getProvinceName());
    }


}
