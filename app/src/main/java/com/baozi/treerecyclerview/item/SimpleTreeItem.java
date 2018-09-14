package com.baozi.treerecyclerview.item;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.baozi.treerecyclerview.base.ViewHolder;

/**
 * 简单样式的item
 */
public class SimpleTreeItem extends TreeItem {
    private int layout;
    private int spanSize;
    private Consumer<ViewHolder> treeClick;
    private Consumer<ViewHolder> treeBind;

    public SimpleTreeItem() {
        this(0, 0);
    }

    public SimpleTreeItem(int layout) {
        this(layout, 0);
    }

    public SimpleTreeItem(int layout, int spanSize) {
        this.layout = layout;
        this.spanSize = spanSize;
    }

    @Override
    public int getLayoutId() {
        return layout;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        if (treeBind != null) {
            treeBind.accept(viewHolder);
        }
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        if (treeClick != null) {
            treeClick.accept(viewHolder);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, RecyclerView.LayoutParams layoutParams, int position) {

    }

    @Override
    public int getSpanSize(int maxSpan) {
        return spanSize == 0 ? spanSize : maxSpan / spanSize;
    }

    public interface Consumer<T> {
        void accept(T t);
    }


    public SimpleTreeItem setTreeClick(Consumer<ViewHolder> treeClick) {
        this.treeClick = treeClick;
        return this;
    }

    public SimpleTreeItem setTreeBind(Consumer<ViewHolder> treeOnBind) {
        this.treeBind = treeOnBind;
        return this;
    }

}
