package com.baozi.demo.item.city;

import android.support.annotation.NonNull;

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
public class ProvinceItemParent extends TreeItemGroup<ProvinceBean> {

    @Override
    public List<TreeItem> initChild(ProvinceBean data) {
        List<TreeItem> items = ItemHelperFactory.createItems(data.citys, this);
        for (int i = 0; i < items.size(); i++) {
            TreeItemGroup treeItem = (TreeItemGroup) items.get(i);
            treeItem.setExpand(true);
        }
        return items;
    }

    @Override
    public int getLayoutId() {
        return R.layout.itme_one;
    }

    @Override
    protected void onExpand() {
        ItemManager itemManager = getItemManager();
        if (itemManager != null) {
            int itemPosition = itemManager.getItemPosition(this);
            List datas = itemManager.getAdapter().getData();
            datas.addAll(itemPosition + 1, getExpandChild());
            itemManager.getAdapter().notifyItemRangeInserted(itemPosition + 1, getExpandChild().size());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder) {
        holder.setText(R.id.tv_content, data.provinceName);
    }

}
