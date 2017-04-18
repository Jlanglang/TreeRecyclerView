package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeParentItem;
import com.baozi.treerecyclerview.viewholder.ItemHelper;

import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class OneItem extends TreeParentItem<CityBean> {
    @Override
    public List<? extends TreeItem> initChildsList() {
        return ItemHelper.createItemListForClass(data.getCitys(), TwoItem.class, this);
    }

    //    @Override
//    protected List<? extends TreeItem> initChildsList(CityBean data) {
////        ArrayList<TreeItem> treeItems = new ArrayList<>();
////        List<CityBean.CitysBean> citys = data.getCitys();
////        if (null == citys) {
////            return null;
////        }
////        List<TreeItem> childItemList =
////        for (int i = 0; i < childItemList.size(); i++) {
////            if (childItemList.get(i).getData().getCityName().equals("朝阳区")) {
////                twoItem.setCanChangeExpand(false, true);
////            }
////        }
//        List<TwoItem> twoItems =
//        return twoItems;
//    }

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
