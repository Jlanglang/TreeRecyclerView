package com.baozi.treerecyclerview.adpater;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.viewholder.TreeParentItem;
import com.baozi.treerecyclerview.viewholder.TreeItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TreeRecyclerViewAdapter<T extends TreeItem> extends RecyclerView.Adapter<ViewHolder> {

    protected TreeRecyclerViewType type;

    protected Context mContext;
    /**
     * 存储所有可见的Node
     */
    protected List<T> mDatas;//处理后的展示数据

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
        mContext = context;
        datas = datas == null ? new ArrayList<T>() : datas;
        if (type == TreeRecyclerViewType.SHOW_ALL) {
            mDatas = new ArrayList<>();
            for (int i = 0; i < datas.size(); i++) {
                T t = datas.get(i);
                mDatas.add(t);
                if (t instanceof TreeParentItem) {
                    List allChilds = ((TreeParentItem) t).getAllChilds();
                    mDatas.addAll(allChilds);
                }
            }
        } else {
            mDatas = datas;
        }
    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     *
     * @param position 触发的条目
     */
    private void expandOrCollapse(int position) {
        TreeItem treeItem = mDatas.get(position);
        if (treeItem instanceof TreeParentItem) {
            TreeParentItem treeParentItem = (TreeParentItem) treeItem;
            boolean expand = treeParentItem.isExpand();
            List allChilds = treeParentItem.getChilds();
            if (expand) {
                mDatas.removeAll(allChilds);
                treeParentItem.onCollapse();
            } else {
                mDatas.addAll(position + 1, allChilds);
                treeParentItem.onExpand();
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
                    TreeItem treeItem = mDatas.get(position);
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        TreeItem treeItem = mDatas.get(position);
        treeItem.onBindViewHolder(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type != TreeRecyclerViewType.SHOW_ALL) {
                    expandOrCollapse(position);
                }
//                treeItem.onTreeItemClickListener.onClick(TreeRecyclerViewAdapter.this, holder, position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getLayoutId();
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    public interface OnTreeItemExpandListener {
        void onCollapse();

        void onExpand();
    }
}