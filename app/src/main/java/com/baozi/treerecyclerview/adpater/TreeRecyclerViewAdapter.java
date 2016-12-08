package com.baozi.treerecyclerview.adpater;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.R;
import com.baozi.treerecyclerview.viewholder.TreeAdapterItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.List;

public class TreeRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    protected Context mContext;
    /**
     * 存储所有可见的Node
     */
    protected List<TreeAdapterItem> mNodes;//处理后的展示数据

    /**
     * 点击的回调接口
     */
    private OnTreeNodeClickListener onTreeNodeClickListener;

    public interface OnTreeNodeClickListener {
        void onClick(TreeAdapterItem node, int position);
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    /**
     * @param context
     * @param datas
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public TreeRecyclerViewAdapter(Context context, List<TreeAdapterItem> datas) {
        mContext = context;
        mNodes = datas;
    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     *
     * @param position
     */
    public void expandOrCollapse(int position) {
        TreeAdapterItem treeAdapterItem = mNodes.get(position);
        if (!treeAdapterItem.isParent()) {
            return;
        }
        if (treeAdapterItem.isExpand()) {
            List list = treeAdapterItem.onGathered();
            mNodes.removeAll(list);
        } else {
            List list = treeAdapterItem.onExpand();
            mNodes.addAll(position + 1, list);
        }
        treeAdapterItem.setExpand(!treeAdapterItem.isExpand());
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    TreeAdapterItem treeAdapterItem = mNodes.get(position);
                    if (treeAdapterItem.getSpansize() == 0) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return treeAdapterItem.getSpansize();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return ViewHolder.createViewHolder(mContext, parent, R.layout.itme_one);
            case 2:
                return ViewHolder.createViewHolder(mContext, parent, R.layout.item_two);
            case 3:
                return ViewHolder.createViewHolder(mContext, parent, R.layout.item_three);
            case 4:
                return ViewHolder.createViewHolder(mContext, parent, R.layout.item_four);
            case 5:
                return ViewHolder.createViewHolder(mContext, parent, R.layout.item_five);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TreeAdapterItem treeAdapterItem = mNodes.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandOrCollapse(position);
                if (onTreeNodeClickListener != null) {
                    onTreeNodeClickListener.onClick(treeAdapterItem, position);
                }
            }
        });
        treeAdapterItem.onBindViewHolder(holder);
    }

    @Override
    public int getItemViewType(int position) {
        return mNodes.get(position).grade();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mNodes == null ? 0 : mNodes.size();
    }
}