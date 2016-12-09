package com.baozi.demo.treeitem;

import com.baozi.demo.R;
import com.baozi.demo.bean.CityBean;
import com.baozi.treerecyclerview.viewholder.TreeAdapterItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class ThreeItem extends TreeAdapterItem<CityBean.CitysBean.AreasBean> {
    public ThreeItem(CityBean.CitysBean.AreasBean data) {
        super(data);
    }

    @Override
    protected List<TreeAdapterItem> initChildsList(CityBean.CitysBean.AreasBean data) {
        ArrayList<TreeAdapterItem> treeAdapterItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FourItem threeItem = new FourItem("我是四级");
            treeAdapterItems.add(threeItem);
        }
        return treeAdapterItems;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_three;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content,data.getAreaName());
    }
}
