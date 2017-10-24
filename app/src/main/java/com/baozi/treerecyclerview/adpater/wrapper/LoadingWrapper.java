package com.baozi.treerecyclerview.adpater.wrapper;

import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;

/**
 * Created by baozi on 2017/4/30.
 * 该装饰请务必使用在最后一次
 */

public class LoadingWrapper<T> extends BaseWrapper<T> {

    private static final int ITEM_TYPE_EMPTY = -3000;
    private static final int ITEM_TYPE_LOADING = -4000;
    private static final int ITEM_LOAD_MORE = -5000;
    private static final int ITEM_LOAD_OVER = -6000;
    private View mEmptyView;
    private int mEmptyLayoutId;

    private View mLoadMoreView;
    private int mLoadMoreLayoutId;

    private View mLoadMoreOverView;
    private int mLoadMoreOverLayoutId;

    private View mLoadingView;
    private int mLoadingLayoutId;

    private Type mType = Type.SUCCESS;

    public enum Type {
        EMPTY, SUCCESS, LOADING, LOAD_MORE, LOAD_OVER
    }

    public LoadingWrapper(BaseRecyclerAdapter<T> adapter) {
        super(adapter);
    }

    private boolean isEmpty() {
        return getCheckItem().checkCount() == 0;
    }

    private boolean isLoading() {
        return mType == Type.LOADING;
    }

    public void setType(Type type) {
        mType = type;
        switch (type) {
            case EMPTY:
                break;
            case SUCCESS:
                break;
            case LOADING:
                notifyDataSetChanged();
                break;
            case LOAD_MORE:
                break;
            case LOAD_OVER:
                break;
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
            if (mLoadingView != null) {
                return ViewHolder.createViewHolder(mLoadMoreView);
            } else {
                return ViewHolder.createViewHolder(parent, mLoadingLayoutId);
            }
        }
        if (viewType == ITEM_LOAD_OVER) {
            if (mLoadMoreOverView != null) {
                return ViewHolder.createViewHolder(mLoadMoreOverView);
            } else {
                return ViewHolder.createViewHolder(parent, mLoadMoreOverLayoutId);
            }
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
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
            if (mType == Type.LOAD_MORE) {
                return ITEM_LOAD_MORE;
            } else {
                return ITEM_LOAD_OVER;
            }
        }
        return mAdapter.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isEmpty() || isLoading() || isLoadMoreViewPos(position)) {
            return;
        }
        mAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        if (isEmpty() || isLoading()) {
            return 1;
        }
        if (mType == Type.LOAD_OVER || mType == Type.LOAD_MORE) {
            return mAdapter.getItemCount() + 1;
        }
        return mAdapter.getItemCount();
    }


    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    public void setEmptyView(int layoutId) {
        mEmptyLayoutId = layoutId;
    }

    public void setLoadingView(View loadingView) {
        mLoadingView = loadingView;
    }

    public void setLoadingView(int layoutId) {
        mLoadingLayoutId = layoutId;
    }

    interface LoadingCallBack {

        void onEmpty();

        void loadMore();

        void loadMoreOver();

        void loading();
    }
}
