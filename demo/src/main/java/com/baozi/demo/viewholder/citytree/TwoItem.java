package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeParentItem;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class TwoItem extends TreeParentItem<CityBean.CitysBean> {

    @Override
    public List<? extends TreeItem> initChildsList() {
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        List<CityBean.CitysBean.AreasBean> citys = data.getAreas();
        if (citys == null) {
            return null;
        }
        for (int i = 0; i < citys.size(); i++) {
            ThreeItem threeItem = new ThreeItem();
            threeItem.setData(citys.get(i));
            threeItem.setParentItem(this);
            treeItems.add(threeItem);
        }
        return treeItems;
    }

    @Override
    public int initLayoutId() {
        return R.layout.item_two;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getCityName());
    }

    @Override
    public boolean isCanChangeExpand() {
        return data.getCityName().equals("朝阳区");
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }
}
