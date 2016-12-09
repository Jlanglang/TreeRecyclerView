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
public class TwoItem extends TreeAdapterItem<CityBean.CitysBean> {

    public TwoItem(CityBean.CitysBean data) {
        super(data);
    }


    @Override
    protected List<TreeAdapterItem> initChildsList(CityBean.CitysBean data) {
        ArrayList<TreeAdapterItem> treeAdapterItems = new ArrayList<>();
        List<CityBean.CitysBean.AreasBean> citys = data.getAreas();
        if (citys == null) {
            return null;
        }
        for (int i = 0; i < citys.size(); i++) {
            ThreeItem threeItem = new ThreeItem(citys.get(i));
            treeAdapterItems.add(threeItem);
        }
        return treeAdapterItems;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_two;
    }


    @Override
    public int initSpansize() {
        return 2;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getCityName());
    }
}
