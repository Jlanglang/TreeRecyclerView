package com.baozi.treerecyclerview.adpater.wrapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.List;

/**
 * Created by baozi on 2017/4/30.
 * 在最后一个，优先级高于loadwrapper,
 */

public class HeaderAndFootWrapper<T> extends BaseWrapper<T> {
    private static final int HEAD_ITEM = 1000;
    private static final int FOOT_ITEM = 2000;
    private SparseArray<View> mHeaderViews = new SparseArray<>();
    private SparseArray<View> mFootViews = new SparseArray<>();
    private boolean headShow = true;
    private boolean footShow = true;
    private int mHeaderSize;
    private int mFootSize;

    public HeaderAndFootWrapper(BaseRecyclerAdapter<T> adapter) {
        super(adapter);
    }

    @Override
    public T getData(int position) {
        return mAdapter.getData(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return ViewHolder.createViewHolder(mHeaderViews.get(viewType));
        } else if (mFootViews.get(viewType) != null) {
            return ViewHolder.createViewHolder(mFootViews.get(viewType));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolderClick(@NonNull ViewHolder holder, View view) {
        int layoutPosition = holder.getLayoutPosition();
        if ((isHeaderViewPos(layoutPosition) || isFooterViewPos(layoutPosition))) {
            return;
        }
        super.onBindViewHolderClick(holder, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            return;
        }
        mAdapter.onBindViewHolder(holder, position);
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
            return mFootViews.keyAt(position - mAdapter.getItemCount() + getFootCount());
        }
        return mAdapter.getItemViewType(position);
    }


    public void addHeaderView(View view) {
        getDatas().add(mHeaderSize, null);//占位
        mHeaderViews.put(HEAD_ITEM + mHeaderViews.size(), view);
        mHeaderSize = mHeaderViews.size();
    }

    public void addFootView(View view) {
        getDatas().add(null);//占位
        mFootViews.put(FOOT_ITEM + mFootViews.size(), view);
        mFootSize = mFootViews.size();
    }

    protected boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    protected boolean isFooterViewPos(int position) {
        int foot = mAdapter.getItemCount() - getFootCount();
        return position >= foot;
    }

    public void setShowHeadView(boolean show) {
        this.headShow = show;
        for (int i = 0; i < mHeaderSize; i++) {
            if (headShow) {
                getDatas().add(0, null);
            } else {
                getDatas().remove(0);
            }
        }
        getItemManager().notifyDataChanged();
        mHeaderSize = getHeadersCount();
    }

    public int getHeadersCount() {
        if (!headShow) {
            return 0;
        }
        return mHeaderSize;
    }

    @Override
    public void setDatas(List<T> datas) {
        int headersCount = getHeadersCount();
        for (int i = 0; i < headersCount; i++) {
            datas.add(0, null);
        }
        int footCount = getFootCount();
        for (int i = 0; i < footCount; i++) {
            datas.add(null);
        }
        super.setDatas(datas);

    }

    public int getFootCount() {
        return mFootSize;
    }
}
