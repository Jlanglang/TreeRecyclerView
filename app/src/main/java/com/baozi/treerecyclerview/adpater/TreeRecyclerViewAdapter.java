package com.baozi.treerecyclerview.adpater;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeParentItem;

import java.util.ArrayList;
import java.util.List;

public class TreeRecyclerViewAdapter<T extends TreeItem> extends RecyclerView.Adapter<ViewHolder> {
    //原始第一级数据
    private List<T> mFristDatas;

    protected TreeRecyclerViewType type;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 展示的items
     */
    private List<T> mShowDatas;

    /**
     * @param context 上下文
     * @param datas   条目数据
     */
    public TreeRecyclerViewAdapter(Context context, List<T> datas) {
        this(context, datas, TreeRecyclerViewType.SHOW_DEFUTAL);
    }

    /**
     * @param context 上下文
     * @param datas   条目数据
     */
    public TreeRecyclerViewAdapter(Context context, List<T> datas, TreeRecyclerViewType type) {
        this.type = type;
        this.mContext = context;
        this.mFristDatas = datas;
        datas = datas == null ? new ArrayList<T>() : datas;
        if (type == TreeRecyclerViewType.SHOW_ALL) {
            mShowDatas = new ArrayList<>();
            for (int i = 0; i < datas.size(); i++) {
                T t = datas.get(i);
                mShowDatas.add(t);
                if (t instanceof TreeParentItem) {
                    List allChilds = ((TreeParentItem) t).getChilds(type);
                    mShowDatas.addAll(allChilds);
                }
            }
        } else {
            mShowDatas = new ArrayList<>();
            for (int i = 0; i < datas.size(); i++) {
                T t = datas.get(i);
                mShowDatas.add(t);
                if (t instanceof TreeParentItem && !((TreeParentItem) t).canExpandOrCollapse()) {
                    List allChilds = ((TreeParentItem) t).getChilds(type);
                    mShowDatas.addAll(allChilds);
                }
            }
        }
    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     *
     * @param position 触发的条目
     */
    private void expandOrCollapse(int position) {
        TreeItem treeItem = mShowDatas.get(position);
        if (treeItem instanceof TreeParentItem && ((TreeParentItem) treeItem).canExpandOrCollapse()) {
            TreeParentItem treeParentItem = (TreeParentItem) treeItem;
            boolean expand = treeParentItem.isExpand();
            List allChilds = treeParentItem.getChilds(type);
            if (expand) {
                mShowDatas.removeAll(allChilds);
                treeParentItem.onCollapse();
                treeParentItem.setExpand(false);
            } else {
                mShowDatas.addAll(position + 1, allChilds);
                treeParentItem.onExpand();
                treeParentItem.setExpand(true);
            }
            notifyDataSetChanged();
        }
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
                    TreeItem treeItem = mShowDatas.get(position);
                    if (treeItem.getSpanSize() == 0) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return treeItem.getSpanSize();
                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.createViewHolder(mContext, parent, viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final TreeItem treeItem = mShowDatas.get(holder.getLayoutPosition());
        treeItem.onAttchApater(this);
        treeItem.onBindViewHolder(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type != TreeRecyclerViewType.SHOW_ALL) {
                    expandOrCollapse(holder.getLayoutPosition());
                }
                treeItem.onClick();
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return mShowDatas.get(position).getLayoutId();
    }

    @Override
    public int getItemCount() {
        return mShowDatas == null ? 0 : mShowDatas.size();
    }

    public List<T> getFristDatas() {
        return mFristDatas;
    }

    public void setDatas(List<T> datas) {
        mShowDatas = datas;
        notifyDataSetChanged();
    }
}