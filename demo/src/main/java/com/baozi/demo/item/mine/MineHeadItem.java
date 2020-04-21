package com.baozi.demo.item.mine;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

public class MineHeadItem extends TreeItem<String> {
    @Override
    public int getLayoutId() {
        return R.layout.item_mine_head;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, RecyclerView.LayoutParams layoutParams, int position) {
        super.getItemOffsets(outRect, layoutParams, position);
        outRect.bottom = 1;
    }
}
