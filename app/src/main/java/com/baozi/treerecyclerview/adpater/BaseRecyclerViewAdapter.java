package com.baozi.treerecyclerview.adpater;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.view.TreeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/4/14.
 */

public class BaseRecyclerViewAdapter<T extends TreeItem> extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    /**
     * 存储原始的items;
     */
    private List<T> mDatas;//处理后的展示数据

    /**
     * @param context 上下文
     * @param datas   条目数据
     */
    public BaseRecyclerViewAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas == null ? new ArrayList<T>() : datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.createViewHolder(mContext, parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T t = mDatas.get(position);
        t.onBindViewHolder(holder);
    }


    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getLayoutId();
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
    public int getItemCount() {
        return mDatas.size();
    }
}
