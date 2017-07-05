package com.baozi.demo.moudle.shoplist;

import android.view.View;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.TreeItem;

/**
 * Created by baozi on 2017/5/3.
 */

public class HeadViewItem extends TreeItem {
    private boolean ischeck;

    @Override
    protected int initLayoutId() {
        return R.layout.item_shoplist_title;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ischeck = !ischeck;
                viewHolder.setChecked(R.id.cb_ischeck, ischeck);
            }
        });
    }
}
