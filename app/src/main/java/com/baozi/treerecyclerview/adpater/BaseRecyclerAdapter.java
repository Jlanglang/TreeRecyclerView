package com.baozi.treerecyclerview.adpater;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.view.BaseItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by baozi on 2017/4/14.
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
                    getDatas().get(layoutPosition).onClick(holder,layoutPosition);
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


//
//    public void addAll(List<T> rows) {
//        if (rows != null) {
//            if (mDatas.size() == 0) {
//                setDatas(rows);
//            } else {
//                mDatas.addAll(rows);
//                notifyDataSetChanged();
//            }
//        }
//    }

//    public void add(int position, T data) {
//        if (data != null && position >= 0) {
//            mDatas.add(position, data);
//            notifyDataSetChanged();
//        }
//    }
//
//    public void set(T data, int position) {
//        if (data != null && position >= 0) {
//            mDatas.set(position, data);
//            notifyDataSetChanged();
//        }
//    }
//
//    public void remove(int position) {
//        mDatas.remove(position);
//        notifyDataSetChanged();
//    }
//
//    public void remove(T data) {
//        mDatas.remove(data);
//        notifyDataSetChanged();
//    }
//
//    public void removeAll(List<T> data) {
//        if (data != null) {
//            mDatas.removeAll(data);
//            notifyDataSetChanged();
//        }
//    }
}
