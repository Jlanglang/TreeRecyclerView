package com.baozi.treerecyclerview.adpater.wrapper;

import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;

/**
 * Created by baozi on 2017/4/30.
 */

public class LoadingWrapper<T> extends BaseWrapper<T> {
    private static final int ITEM_TYPE_EMPTY = 3000;
    private static final int ITEM_TYPE_LOADING = 4000;
    private static final int ITEM_LOAD_MORE = 5000;
    private View mEmptyView;
    private int mEmptyLayoutId;

    private View mLoadMoreView;
    private int mLoadMoreLayoutId;

    private View mLoadMoreOverView;
    private int mLoadMoreOverLayoutId;

    private View mLoadingView;
    private int mLoadingLayoutId;
    private boolean isLoading;


    public LoadingWrapper(BaseRecyclerAdapter<T> adapter) {
        super(adapter);
    }

    private boolean isEmpty() {
        return (mEmptyView != null || mEmptyLayoutId != 0) && mAdapter.getItemCount() == 0;
    }

    private boolean isLoading() {
        return (mLoadingView != null || mLoadingLayoutId != 0) && isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        if (loading) {

        } else {

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOADING) {
            if (mLoadingView != null) {
                return ViewHolder.createViewHolder(mLoadingView);
            } else {
                return ViewHolder.createViewHolder(parent, mLoadingLayoutId);
            }
        }
        if (viewType == ITEM_TYPE_EMPTY) {
            if (mEmptyView != null) {
                return ViewHolder.createViewHolder(mEmptyView);
            } else {
                return ViewHolder.createViewHolder(parent, mEmptyLayoutId);
            }
        }
        if (viewType == ITEM_LOAD_MORE) {
            if (mEmptyView != null) {
                return ViewHolder.createViewHolder(mLoadMoreView);
            } else {
                return ViewHolder.createViewHolder(parent, mLoadingLayoutId);
            }
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    private boolean isLoadMoreViewPos(int position) {
        return position >= mAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoading()) {
            return ITEM_TYPE_LOADING;
        }
        if (isEmpty()) {
            return ITEM_TYPE_EMPTY;
        }
        if (isLoadMoreViewPos(position)) {
            return ITEM_LOAD_MORE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isEmpty() || isLoading() || isLoadMoreViewPos(position)) {
            return;
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        if (isEmpty() || isLoading()) return 1;
        return mAdapter.getItemCount();
    }


    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    public void setEmptyView(int layoutId) {
        mEmptyLayoutId = layoutId;
    }

}
