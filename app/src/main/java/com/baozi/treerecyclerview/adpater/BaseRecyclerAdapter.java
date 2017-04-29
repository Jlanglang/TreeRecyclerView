package com.baozi.treerecyclerview.adpater;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.view.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通BaseRecyclerAdapter,itme无父子关系.
 * 限定泛型为BaseItem的子类.
 * 通过BaseItem去处理ViewHolder
 */
public class BaseRecyclerAdapter<T extends BaseItem> extends
        RecyclerView.Adapter<ViewHolder> {
    /**
     * items;
     */
    private List<T> mDatas;//展示数据

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.createViewHolder(parent.getContext(), parent, viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        T t = mDatas.get(position);
        t.onBindViewHolder(holder);
        if (!holder.itemView.hasOnClickListeners()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    getDatas().get(layoutPosition).onClick();
                }
            });
        }
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
                    BaseItem baseItem = mDatas.get(position);
                    if (baseItem.getSpanSize() == 0) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return baseItem.getSpanSize();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public List<T> getDatas() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        return mDatas;
    }

    public void setDatas(List<T> datas) {
        if (datas != null) {
            mDatas = datas;
            notifyDataSetChanged();
        }
    }


}
