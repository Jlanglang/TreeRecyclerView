package com.baozi.treerecyclerview.treeitem;

import com.baozi.treerecyclerview.R;
import com.baozi.treerecyclerview.bean.CityBean;
import com.baozi.treerecyclerview.viewholder.TreeAdapterItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class OneItem extends TreeAdapterItem<CityBean> {

    public OneItem(CityBean data) {
        super(data);
    }

    @Override
    protected List<TreeAdapterItem> initChildsList(CityBean data) {
        ArrayList<TreeAdapterItem> treeAdapterItems = new ArrayList<>();
        List<CityBean.CitysBean> citys = data.getCitys();
        if (citys == null) {
            return null;
        }
        for (int i = 0; i < citys.size(); i++) {
            TwoItem twoItem = new TwoItem(citys.get(i));
            treeAdapterItems.add(twoItem);
        }
        return treeAdapterItems;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.itme_one;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content,data.getProvinceName());
    }
}
