package com.baozi.treerecyclerview.adpater.wrapper;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;

/**
 * Created by baozi on 2017/4/30.
 */

public class HeaderAndFootWrapper<T> extends BaseWrapper<T> {
    private static final int HEAD_ITEM = 1000;
    private static final int FOOT_ITEM = 2000;
    private SparseArray<View> mHeaderViews = new SparseArray<>();
    private SparseArray<View> mFootViews = new SparseArray<>();
    private boolean headShow = true;
    private boolean footShow = true;

    public HeaderAndFootWrapper(BaseRecyclerAdapter<T> adapter) {
        super(adapter);
        setCheckItem(new CheckItem() {
            @Override
            public int checkPosition(int position) {
                return position - getHeadersCount();
            }
        });
    }


    @Override
    public T getData(int position) {
        return mAdapter.getData(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return ViewHolder.createViewHolder(mHeaderViews.get(viewType));
        } else if (mFootViews.get(viewType) != null) {
            return ViewHolder.createViewHolder(mFootViews.get(viewType));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolderClick(ViewHolder holder, View view) {
        int layoutPosition = holder.getLayoutPosition();
        if ((isHeaderViewPos(layoutPosition) || isFooterViewPos(layoutPosition))) {
            return;
        }
        super.onBindViewHolderClick(holder, view);
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

    public int getItemSpanSize(int position) {
        if (isHeaderViewPos(position)) {
            return 0;
        }
        return mAdapter.getItemSpanSize(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - mAdapter.getItemCount());
        }
        return mAdapter.getItemViewType(position - getHeadersCount());
    }


    public void addHeaderView(View view) {
        mHeaderViews.put(HEAD_ITEM + mHeaderViews.size(), view);
    }

    public void addFootView(View view) {
        mFootViews.put(FOOT_ITEM + mFootViews.size(), view);
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + mAdapter.getItemCount();
    }

    public void setShowHeadView(boolean show) {
        this.headShow = show;
    }

    public int getHeadersCount() {
        if (!headShow) {
            return 0;
        }
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }


}
