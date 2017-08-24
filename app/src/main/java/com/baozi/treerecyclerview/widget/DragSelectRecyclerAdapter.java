package com.baozi.treerecyclerview.widget;

import android.os.Bundle;
import android.view.View;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;

import java.util.ArrayList;

/**
 * @author Aidan Follestad (afollestad)
 */
public abstract class DragSelectRecyclerAdapter<T> extends BaseRecyclerAdapter<T> {

    public interface SelectionListener {
        void onDragSelectionChanged(int count);
    }

    private ArrayList<Integer> mSelectedIndices;
    private SelectionListener mSelectionListener;
    private int mLastCount = -1;
    private int mMaxSelectionCount = -1;

    private void fireSelectionListener() {
        if (mLastCount == mSelectedIndices.size())
            return;
        mLastCount = mSelectedIndices.size();
        if (mSelectionListener != null)
            mSelectionListener.onDragSelectionChanged(mLastCount);
    }

    protected DragSelectRecyclerAdapter() {
        mSelectedIndices = new ArrayList<>();
    }

    @Override
    public void onBindViewHolderClick(ViewHolder holder, View view) {
        super.onBindViewHolderClick(holder, view);
        holder.itemView.setTag(holder);
    }

    public void setMaxSelectionCount(int maxSelectionCount) {
        this.mMaxSelectionCount = maxSelectionCount;
    }

    public void setSelectionListener(SelectionListener selectionListener) {
        this.mSelectionListener = selectionListener;
    }

    public void saveInstanceState(Bundle out) {
        saveInstanceState("selected_indices", out);
    }

    public void saveInstanceState(String key, Bundle out) {
        out.putSerializable(key, mSelectedIndices);
    }

    public void restoreInstanceState(Bundle in) {
        restoreInstanceState("selected_indices", in);
    }

    public void restoreInstanceState(String key, Bundle in) {
        if (in != null && in.containsKey(key)) {
            //noinspection unchecked
            mSelectedIndices = (ArrayList<Integer>) in.getSerializable(key);
            if (mSelectedIndices == null) mSelectedIndices = new ArrayList<>();
            else fireSelectionListener();
        }
    }

    public final void setSelected(int index, boolean selected) {
        if (!isIndexSelectable(index))
            selected = false;
        if (selected) {
            if (!mSelectedIndices.contains(index) &&
                    (mMaxSelectionCount == -1 ||
                            mSelectedIndices.size() < mMaxSelectionCount)) {
                mSelectedIndices.add(index);
                notifyDataSetChanged();
            }
        } else if (mSelectedIndices.contains(index)) {
            mSelectedIndices.remove((Integer) index);
            notifyDataSetChanged();
        }
        fireSelectionListener();
    }

    public final boolean toggleSelected(int index) {
        boolean selectedNow = false;
        if (isIndexSelectable(index)) {
            if (mSelectedIndices.contains(index)) {
                mSelectedIndices.remove((Integer) index);
            } else if (mMaxSelectionCount == -1 ||
                    mSelectedIndices.size() < mMaxSelectionCount) {
                mSelectedIndices.add(index);
                selectedNow = true;
            }
            notifyDataSetChanged();
        }
        fireSelectionListener();
        return selectedNow;
    }

    protected boolean isIndexSelectable(int index) {
        return true;
    }

    public final void selectRange(int from, int to, int min, int max) {
        if (from == to) {
            // Finger is back on the initial item, unselect everything else
            for (int i = min; i <= max; i++) {
                if (i == from) continue;
                setSelected(i, false);
            }
            fireSelectionListener();
            return;
        }

        if (to < from) {
            // When selecting from one to previous items
            for (int i = to; i <= from; i++)
                setSelected(i, true);
            if (min > -1 && min < to) {
                // Unselect items that were selected during this drag but no longer are
                for (int i = min; i < to; i++) {
                    if (i == from) continue;
                    setSelected(i, false);
                }
            }
            if (max > -1) {
                for (int i = from + 1; i <= max; i++)
                    setSelected(i, false);
            }
        } else {
            // When selecting from one to next items
            for (int i = from; i <= to; i++)
                setSelected(i, true);
            if (max > -1 && max > to) {
                // Unselect items that were selected during this drag but no longer are
                for (int i = to + 1; i <= max; i++) {
                    if (i == from) continue;
                    setSelected(i, false);
                }
            }
            if (min > -1) {
                for (int i = min; i < from; i++)
                    setSelected(i, false);
            }
        }
        fireSelectionListener();
    }

    public final void clearSelected() {
        mSelectedIndices.clear();
        notifyDataSetChanged();
        fireSelectionListener();
    }

    public final int getSelectedCount() {
        return mSelectedIndices.size();
    }

    public final ArrayList<Integer> getSelectedIndices() {
        if (mSelectedIndices == null) {
            mSelectedIndices = new ArrayList<>();
        }
        return mSelectedIndices;
    }

    public final boolean isIndexSelected(int index) {
        return mSelectedIndices.contains(index);
    }
}