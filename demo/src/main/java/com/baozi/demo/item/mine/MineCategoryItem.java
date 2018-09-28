package com.baozi.demo.item.mine;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.baozi.demo.R;
import com.baozi.demo.bean.mine.MineCategoryBean;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

/**
 * 分类
 */
public class MineCategoryItem extends TreeItem<MineCategoryBean> {
    @Override
    public int getLayoutId() {
        return R.layout.item_mine_category;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        viewHolder.setText(R.id.tv_content, data.content);

        viewHolder.setVisible(R.id.tv_title, !TextUtils.isEmpty(data.title));
        viewHolder.setText(R.id.tv_title, data.title);

        viewHolder.setVisible(R.id.iv_content, !TextUtils.isEmpty(data.url));

    }

    @Override
    public int getSpanSize(int maxSpan) {
        return maxSpan / 4;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, RecyclerView.LayoutParams layoutParams, int position) {
        super.getItemOffsets(outRect, layoutParams, position);
        if (data.isEnd){
            outRect.bottom = 20;
        }else {
            outRect.bottom = 0;
        }
    }
}
