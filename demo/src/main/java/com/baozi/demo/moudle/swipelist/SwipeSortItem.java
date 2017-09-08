package com.baozi.demo.moudle.swipelist;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baozi.demo.R;
import com.baozi.demo.moudle.sortList.SortChildItem;
import com.baozi.treerecyclerview.item.SwipeItem;
import com.baozi.treerecyclerview.widget.swipe.SwipeLayout;
import com.baozi.treerecyclerview.base.ViewHolder;

/**
 * Created by baozi on 2017/8/20.
 */

public class SwipeSortItem extends SortChildItem implements SwipeItem {
    @Override
    public int getSwipeLayoutId() {
        return R.layout.layout_delete;
    }

    @Override
    public SwipeLayout.DragEdge getDragEdge() {
        return SwipeLayout.DragEdge.Right;
    }

    @Override
    public void onBindSwipeView(ViewHolder viewHolder, int position) {
        viewHolder.setOnClickListener(R.id.tv_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "删除", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        super.onClick(viewHolder);
    }
}
