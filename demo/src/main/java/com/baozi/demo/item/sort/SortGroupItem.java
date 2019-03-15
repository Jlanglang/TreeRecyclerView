package com.baozi.demo.item.sort;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.baozi.demo.R;
import com.baozi.demo.item.swipe.SwipeSortItem;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSortItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/8/19.
 */

public class SortGroupItem extends TreeSortItem {
    @Override
    protected List<TreeItem> initChildList(Object childs) {
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SwipeSortItem sortChildItem = new SwipeSortItem();
            sortChildItem.setData(i + "");
            treeItems.add(sortChildItem);
        }
        return treeItems;
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_sort_group;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        if (isExpand()) {
            viewHolder.setImageResource(R.id.iv_right, R.drawable.ic_keyboard_arrow_down_black_24dp);
        } else {
            viewHolder.setImageResource(R.id.iv_right, R.drawable.ic_keyboard_arrow_right_black_24dp);
        }
        viewHolder.setText(R.id.tv_content, (String) getSortKey());
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        super.onClick(viewHolder);
        if (isExpand()) {
            viewHolder.setImageResource(R.id.iv_right, R.drawable.ic_keyboard_arrow_down_black_24dp);
        } else {
            viewHolder.setImageResource(R.id.iv_right, R.drawable.ic_keyboard_arrow_right_black_24dp);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, RecyclerView.LayoutParams layoutParams, int position) {
        super.getItemOffsets(outRect, layoutParams, position);
        outRect.top = 10;
        if (position == 0) {
            outRect.top = 0;
        }
    }
}
