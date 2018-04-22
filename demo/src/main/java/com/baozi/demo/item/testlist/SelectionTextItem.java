package com.baozi.demo.item.testlist;

import com.baozi.demo.R;
import com.baozi.demo.bean.ContentBean;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

/**
 * Created by baozi on 2017/4/27.
 */

public class SelectionTextItem extends TreeItem<ContentBean> {
    @Override
    public int getLayoutId() {
        return R.layout.testlist_selection_text;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        getData().getNumber();
    }
}
