package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.viewholder.TreeParentItem;

import java.util.List;

/**
 */
public class TwoItem extends TreeParentItem<CityBean.CitysBean> {

    public TwoItem(CityBean.CitysBean data) {
        super(data);
    }

    @Override
    protected void initChildsList(CityBean.CitysBean data) {
//        ArrayList<TreeItem> treeItems = new ArrayList<>();
        List<CityBean.CitysBean.AreasBean> citys = data.getAreas();
        if (citys == null) {
            return ;
        }
        for (int i = 0; i < citys.size(); i++) {
            ThreeItem threeItem = new ThreeItem(citys.get(i));
            addView(threeItem);
        }
//        return treeItems;
    }

    @Override
    public int initLayoutId() {
        return R.layout.item_two;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getCityName());
    }
}
