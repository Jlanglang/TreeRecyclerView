package com.baozi.treerecyclerview.adpater.wrapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.manager.ItemManager;

import java.util.List;

/**
 * Created by baozi on 2017/4/30.
 * 在最后一个，优先级高于loadwrapper,
 */

public class HeaderAndFootWrapper<T> extends BaseWrapper<T> {
    private static final int HEAD_ITEM = 1000;
    //    private static final int FOOT_ITEM = 2000;
    private SparseArray<View> mHeaderViews = new SparseArray<>();
    //    private SparseArray<View> mFootViews = new SparseArray<>();
    private boolean headShow = true;
    //    private boolean footShow = true;
    private int mHeaderSize;
    //    private int mFootSize;

    public HeaderAndFootWrapper(BaseRecyclerAdapter<T> adapter) {
        super(adapter);
        getItemManager().addCheckItemInterfaces(new ItemManager.CheckItemInterface() {
            @Override
            public int itemToDataPosition(int position) {
                return position - getHeadersCount();
            }

            @Override
            public int dataToItemPosition(int index) {
                return index + getHeadersCount();
            }
        });
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
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolderClick(@NonNull ViewHolder holder, View view) {
        int layoutPosition = holder.getLayoutPosition();
        if ((isHeaderViewPos(layoutPosition)
//                || isFooterViewPos(layoutPosition)
        )) {
            return;
        }
        super.onBindViewHolderClick(holder, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (isHeaderViewPos(position)
//                || isFooterViewPos(position)
                ) {
            return;
        }
        super.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + mAdapter.getItemCount();
    }

    public int getItemSpanSize(int position) {
        if (isHeaderViewPos(position)) {
            return 0;
        }
        return super.getItemSpanSize(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        }
//        else if (isFooterViewPos(position)) {
//            return mFootViews.keyAt(position - mAdapter.getItemCount() + getFootCount());
//        }
        return super.getItemViewType(position - getHeadersCount());
    }


    public void addHeaderView(View view) {
        int size = mHeaderViews.size();
        mHeaderViews.put(HEAD_ITEM + size, view);
        mHeaderSize++;
    }

    @Deprecated
    public void addFootView(View view) {
    }

    protected boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    @Deprecated
    protected boolean isFooterViewPos(int position) {
        return false;
    }


    public void setShowHeadView(boolean show) {
        this.headShow = show;
        int size = mHeaderViews.size();
        for (int i = 0; i < size; i++) {
            View view = mHeaderViews.valueAt(i);
            view.setVisibility(show ? View.VISIBLE : View.GONE);
        }
        mHeaderSize = size;
    }

    public int getHeadersCount() {
        if (!headShow) {
            return 0;
        }
        return mHeaderSize;
    }
}
