package com.baozi.demo.moudle.testlist;

import com.baozi.demo.R;
import com.baozi.demo.moudle.testlist.bean.ContentBean;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItem;

/**
 * Created by baozi on 2017/4/27.
 */

public class SelectionTextItem extends TreeItem<ContentBean> {
    @Override
    protected int initLayoutId() {
        return R.layout.testlist_selection_text;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        getData().getNumber();
    }
}
