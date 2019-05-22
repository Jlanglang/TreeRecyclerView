package com.baozi.treerecyclerview.adpater.wrapper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
    private View mEmptyView;
    private int mEmptyLayoutId;
    private View mLoadingView;
    private int mLoadingLayoutId;
    private LoadMoreItem mLoadMoreItem;
    private LoadMoreListener loadMoreListener;
    private boolean initLoadMore;
    private Type mType;

    public enum Type {
        EMPTY, REFRESH_OVER, @Deprecated SUCCESS, LOADING, LOAD_MORE, LOAD_ERROR, LOAD_OVER
    }

    public LoadingWrapper(BaseRecyclerAdapter<T> adapter) {
        super(adapter);
    }

    private boolean isEmpty() {
        return getDatas().size() == 0;
    }

    private boolean isLoading() {
        return mType == Type.LOADING;
    }

    private boolean isLoadMoreViewPos(int position) {
        return position >= mAdapter.getItemCount();
    }

    public void setType(Type type) {
        switch (type) {
            case EMPTY:
                break;
            case SUCCESS:
            case REFRESH_OVER:
                break;
            case LOADING:
                if (mLoadingView == null && mLoadingLayoutId == 0) {
                    return;
                }
                notifyDataSetChanged();
                break;
            case LOAD_MORE:
            case LOAD_ERROR:
            case LOAD_OVER:
                if (mLoadMoreItem != null) {
                    mLoadMoreItem.setType(type);
                }
                break;
        }
        mType = type;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (mEmptyView == null && mEmptyLayoutId == 0) {
            mEmptyView = new View(recyclerView.getContext());
        }
        if (mLoadingView == null && mLoadingLayoutId == 0) {
            mLoadingView = new View(recyclerView.getContext());
        }
        setType(Type.LOADING);
        if (mLoadMoreItem != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (loadMoreListener == null) return;
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int itemCount = getDatas().size();
                    int lastPosition = checkPosition(layoutManager.findLastVisibleItemPosition());
                    //如果当前不是正在加载更多，并且到了该加载更多的位置，加载更多。
                    int lastVisibleIndex = mLoadMoreItem.getLastVisibleIndex() == 0 ? 1 : mLoadMoreItem.getLastVisibleIndex();
                    if (lastPosition >= (itemCount - lastVisibleIndex) && itemCount >= (mLoadMoreItem.getMinPageSize() - lastVisibleIndex)) {
                        loadMoreListener.loadMore(mType);
                    }
                }
            });
        }
    }

    @Override
    public int getItemSpanSize(int position, int maxSpan) {
        if ((isEmpty() || isLoading()) && position == 0) {
            return maxSpan;
        }
        if (isLoadMoreViewPos(position)) {
            return maxSpan;
        }
        return super.getItemSpanSize(position, maxSpan);
    }

    public LoadMoreListener getLoadMoreListener() {
        return loadMoreListener;
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOADING) {
            if (mLoadingLayoutId > 0) {
                return ViewHolder.createViewHolder(parent, mLoadingLayoutId);
            } else {
                return ViewHolder.createViewHolder(mLoadingView);
            }
        }
        if (viewType == ITEM_TYPE_EMPTY) {
            if (mEmptyLayoutId > 0) {
                return ViewHolder.createViewHolder(parent, mEmptyLayoutId);
            } else {
                return ViewHolder.createViewHolder(mEmptyView);
            }
        }
        if (viewType == ITEM_LOAD_MORE) {
            return ViewHolder.createViewHolder(mLoadMoreItem.getLoadMoreView());
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
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
        return mAdapter.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        if (!initLoadMore) {//没有初始化load moreItem
            return mAdapter.getItemCount();
        }
        if (mType == Type.LOAD_ERROR || mType == Type.LOAD_OVER) {//支持手动设置状态。但不支持直接显示loadmore
            return mAdapter.getItemCount() + 1;
        }
        if (mAdapter.getItemCount() >= mLoadMoreItem.getMinPageSize()) {//当符合最小加载更多条目数时
            return mAdapter.getItemCount() + 1;
        }
        return mAdapter.getItemCount();
    }

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
        mEmptyLayoutId = 0;
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

    public void setLoadMore(LoadMoreItem loadMoreItem) {
        mLoadMoreItem = loadMoreItem;
        initLoadMore = mLoadMoreItem != null;
    }

    public interface LoadMoreListener {
        void loadMore(Type type);
    }

    public abstract static class LoadMoreItem {
        private FrameLayout mLayout;
        private View loadMoreView;
        private View loadOverView;
        private View loadErrorView;

        public LoadMoreItem(Context context) {
            mLayout = new FrameLayout(context);
            mLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            loadErrorView = getLoadErrorView();
            if (loadErrorView != null) {
                mLayout.addView(loadErrorView);
            } else {
                loadErrorView = new View(context);
            }

            loadOverView = getLoadOverView();
            int loadOverLayout = getLoadOverLayout();
            if (loadOverView != null) {
                mLayout.addView(loadOverView);
            } else if (getLoadOverLayout() > 0) {
                loadOverView = LayoutInflater.from(context).inflate(loadOverLayout, mLayout, false);
                mLayout.addView(loadOverView);
            } else {
                loadOverView = new View(context);
            }

            int loadMoreLayout = getLoadMoreLayout();
            if (loadMoreLayout > 0) {
                loadMoreView = LayoutInflater.from(context).inflate(loadMoreLayout, mLayout, false);
                mLayout.addView(loadMoreView);
            } else {
                loadMoreView = new View(context);
            }
        }

        public View getLoadMoreView() {
            return mLayout;
        }

        //倒数第几条开始加载更多
        public int getLastVisibleIndex() {
            return 0;
        }

        public View getLoadOverView() {
            return null;
        }

        public View getLoadErrorView() {
            return null;
        }

        void setType(Type type) {
            loadErrorView.setVisibility(View.GONE);
            loadMoreView.setVisibility(View.GONE);
            loadOverView.setVisibility(View.GONE);
            switch (type) {
                case LOAD_MORE:
                    loadMoreView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_OVER:
                    loadOverView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_ERROR:
                    loadErrorView.setVisibility(View.VISIBLE);
                    break;
            }
        }

        public abstract int getLoadMoreLayout();

        public abstract int getLoadOverLayout();

        //屏幕可见条目数
        public abstract int getMinPageSize();
    }
}
