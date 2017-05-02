package com.baozi.treerecyclerview.adpater.wapper;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.baozi.treerecyclerview.adpater.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.adpater.ItemManager;
import com.baozi.treerecyclerview.helper.ItemHelper;
import com.baozi.treerecyclerview.view.ViewHolder;

import java.util.List;

/**
 * Created by baozi on 2017/4/30.
 */

public class HeaderAndFootWapper<T extends BaseItem> extends BaseRecyclerAdapter<T> {
    private BaseRecyclerAdapter<T> mAdapter;
    private SparseArray<BaseItem> mHeaderViews = new SparseArray<>();
    private SparseArray<BaseItem> mFootViews = new SparseArray<>();

    public HeaderAndFootWapper(BaseRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
        mAdapter.setItemManager(getItemManager());
        mAdapter.setCheckItem(new CheckItem() {
            @Override
            public boolean checkItemPosition(int position) {
                return !(isHeaderViewPos(position) || isFooterViewPos(position));
            }

            @Override
            public int getItemPosition(int position) {
                return position - getHeadersCount();
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            return;
        }
        mAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + mAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.get(position).getLayoutId();
        } else if (isFooterViewPos(position)) {
            return mFootViews.get(position - getHeadersCount() - mAdapter.getItemCount()).getLayoutId();
        }
        return mAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mAdapter.onAttachedToRecyclerView(recyclerView);
    }

    public void addHeaderView(BaseItem view) {
        mHeaderViews.put(mHeaderViews.size(), view);
    }

    public void addFootView(BaseItem view) {
        mFootViews.put(mFootViews.size(), view);
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + mAdapter.getItemCount();
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    @Override
    public List<T> getDatas() {
        return mAdapter.getDatas();
    }

    @Override
    public void setDatas(List<T> datas) {
        mAdapter.setDatas(datas);
    }

}
