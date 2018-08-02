package com.baozi.treerecyclerview.adpater.wrapper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.manager.ItemManager;

import java.util.List;

/**
 * Created by baozi on 2017/5/16.
 */

public class BaseWrapper<T> extends BaseRecyclerAdapter<T> {

    protected BaseRecyclerAdapter<T> mAdapter;

    public BaseWrapper(BaseRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
        mAdapter.getItemManager().setAdapter(this);
    }

    @Override
    public void onBindViewHolderClick(@NonNull ViewHolder holder, View view) {
        mAdapter.onBindViewHolderClick(holder, view);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        mAdapter.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemViewType(int position) {
        return mAdapter.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount();
    }


    @Override
    public int getLayoutId(int position) {
        return mAdapter.getLayoutId(position);
    }

    @Override
    public T getData(int position) {
        return mAdapter.getData(position);
    }

    @Override
    public List<T> getDatas() {
        return mAdapter.getDatas();
    }

    @Override
    public void setDatas(List<T> datas) {
        mAdapter.setDatas(datas);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, T t, int position) {
//        mAdapter.onBindViewHolder(holder, t, position);
//    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mAdapter.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mAdapter.setOnItemLongClickListener(onItemLongClickListener);
    }

    @Override
    public int getItemSpanSize(int position) {
        return mAdapter.getItemSpanSize(position);
    }

    @Override
    public ItemManager<T> getItemManager() {
        return mAdapter.getItemManager();
    }

    @Override
    public void setItemManager(ItemManager<T> itemManager) {
        mAdapter.setItemManager(itemManager);
    }
}
