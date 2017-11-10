package com.baozi.treerecyclerview.adpater.wrapper;

import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.SwipeItem;
import com.baozi.treerecyclerview.widget.swipe.SwipeItemMangerInterface;
import com.baozi.treerecyclerview.widget.swipe.SwipeLayout;
import com.baozi.treerecyclerview.widget.swipe.SwipeMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class SwipeWrapper extends BaseWrapper {
    private static final int SWIPE_ITEM = 6666;
    private SwipeItemMangerInterface mSwipeManger;
    private HashMap<ViewHolder, SwipeLayout> swipeLayoutHashMap = new HashMap<>();
    private SparseIntArray swipeItemSparseArray = new SparseIntArray();

    public SwipeWrapper(BaseRecyclerAdapter adapter) {
        super(adapter);
    }

    public SwipeItemMangerInterface getSwipeManger() {
        if (mSwipeManger == null) {
            mSwipeManger = new SwipeItemMangerImpl();
        }
        return mSwipeManger;
    }

    public void setmSwipeManger(SwipeItemMangerInterface mSwipeManger) {
        this.mSwipeManger = mSwipeManger;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int i = swipeItemSparseArray.get(viewType, -1);
        if (i != -1) {
            SwipeLayout swipeLayout = new SwipeLayout(parent.getContext());
            View inflate = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            swipeLayout.setLayoutParams(inflate.getLayoutParams());
            swipeLayout.addView(inflate);
            ViewHolder swipeViewHolder = ViewHolder.createViewHolder(swipeLayout);
            swipeLayoutHashMap.put(swipeViewHolder, swipeLayout);
            super.onBindViewHolderClick(swipeViewHolder, inflate);
            return swipeViewHolder;
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = super.getItemViewType(position);
        int i = swipeItemSparseArray.get(itemViewType, -1);
        if (i == -1) {//说明该type不存在;
            Object o = getData(getCheckItem().checkPosition(position));
            if (o instanceof SwipeItem) {
                swipeItemSparseArray.put(itemViewType, SWIPE_ITEM + swipeItemSparseArray.size());
            }
        }
        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int checkPosition = getCheckItem().checkPosition(position);
        Object data = getData(checkPosition);
        if (data instanceof SwipeItem) {
            SwipeLayout swipeLayout = (SwipeLayout) holder.itemView;
            checkSwipeLayout(holder, (SwipeItem) data);
            getSwipeManger().bind(swipeLayout, ((SwipeItem) data).getSwipeLayoutId(), checkPosition);
            ((SwipeItem) data).onBindSwipeView(holder, position);
        }
        super.onBindViewHolder(holder, position);
    }

    private void checkSwipeLayout(ViewHolder holder, SwipeItem data) {
        SwipeLayout view = swipeLayoutHashMap.get(holder);
        Map<SwipeLayout.DragEdge, View> dragEdgeMap = view.getDragEdgeMap();
        if (dragEdgeMap.get(data.getDragEdge()) == null) {
            View inflate = LayoutInflater.from(view.getContext()).inflate(data.getSwipeLayoutId(), null);
            view.addDrag(data.getDragEdge(), inflate, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!getSwipeManger().isOpen(SwipeItemMangerImpl.INVALID_POSITION)) {
                    getSwipeManger().closeAllItems();
                }
            }
        });
    }
    /**
     * Swipe    ItemMangerImpl is a helper class to help all the adapters to maintain open status.
     */
    private class SwipeItemMangerImpl implements SwipeItemMangerInterface {
        private SwipeMode mode = SwipeMode.Single;
        public static final int INVALID_POSITION = -1;

        protected int mOpenPosition = INVALID_POSITION;

        protected Set<Integer> mOpenPositions = new HashSet<Integer>();
        protected Set<SwipeLayout> mShownLayouts = new HashSet<SwipeLayout>();

        public SwipeItemMangerImpl() {
        }

        public SwipeMode getMode() {
            return mode;
        }

        public void setMode(SwipeMode mode) {
            this.mode = mode;
            mOpenPositions.clear();
            mShownLayouts.clear();
            mOpenPosition = INVALID_POSITION;
        }

        public void bind(SwipeLayout swipeLayout, int resId, int position) {
            if (swipeLayout.getTag(resId) == null) {
                OnLayoutListener onLayoutListener = new OnLayoutListener(position);
                SwipeMemory swipeMemory = new SwipeMemory(position);
                swipeLayout.addSwipeListener(swipeMemory);
                swipeLayout.addOnLayoutListener(onLayoutListener);
                swipeLayout.setTag(resId, new ValueBox(position, swipeMemory, onLayoutListener));
                mShownLayouts.add(swipeLayout);
            } else {
                ValueBox valueBox = (ValueBox) swipeLayout.getTag(resId);
                valueBox.swipeMemory.setPosition(position);
                valueBox.onLayoutListener.setPosition(position);
                valueBox.position = position;
            }
        }

        @Override
        public void openItem(int position) {
            if (mode == SwipeMode.Multiple) {
                if (!mOpenPositions.contains(position))
                    mOpenPositions.add(position);
            } else {
                mOpenPosition = position;
            }
            getItemManager().notifyDataChanged();
        }

        @Override
        public void closeItem(int position) {
            if (mode == SwipeMode.Multiple) {
                mOpenPositions.remove(position);
            } else {
                if (mOpenPosition == position)
                    mOpenPosition = INVALID_POSITION;
            }
            getItemManager().notifyDataChanged();
        }

        @Override
        public void closeAllExcept(SwipeLayout layout) {
            for (SwipeLayout s : mShownLayouts) {
                if (s != layout)
                    s.close();
            }
        }

        @Override
        public void closeAllItems() {
            if (mode == SwipeMode.Multiple) {
                mOpenPositions.clear();
            } else {
                mOpenPosition = INVALID_POSITION;
            }
            for (SwipeLayout s : mShownLayouts) {
                s.close();
            }
        }

        @Override
        public void removeShownLayouts(SwipeLayout layout) {
            mShownLayouts.remove(layout);
        }

        @Override
        public List<Integer> getOpenItems() {
            if (mode == SwipeMode.Multiple) {
                return new ArrayList<Integer>(mOpenPositions);
            } else {
                return Collections.singletonList(mOpenPosition);
            }
        }

        @Override
        public List<SwipeLayout> getOpenLayouts() {
            return new ArrayList<SwipeLayout>(mShownLayouts);
        }

        @Override
        public boolean isOpen(int position) {
            if (mode == SwipeMode.Multiple) {
                return mOpenPositions.contains(position);
            } else {
                return mOpenPosition == position;
            }
        }

        private class ValueBox {
            OnLayoutListener onLayoutListener;
            SwipeMemory swipeMemory;
            int position;

            ValueBox(int position, SwipeMemory swipeMemory, OnLayoutListener onLayoutListener) {
                this.swipeMemory = swipeMemory;
                this.onLayoutListener = onLayoutListener;
                this.position = position;
            }
        }

        private class OnLayoutListener implements SwipeLayout.OnLayout {

            private int position;

            OnLayoutListener(int position) {
                this.position = position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            @Override
            public void onLayout(SwipeLayout v) {
            }

        }

        private class SwipeMemory implements SwipeLayout.SwipeListener {

            private int position;

            SwipeMemory(int position) {
                this.position = position;
            }

            @Override
            public void onClose(SwipeLayout layout) {
                if (mode == SwipeMode.Multiple) {
                    mOpenPositions.remove(position);
                } else {
                    if (mOpenPosition == position) {
                        mOpenPosition = INVALID_POSITION;
                    }
                }
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }

            @Override
            public void onStartOpen(SwipeLayout layout) {
                if (mode == SwipeMode.Single) {
                    closeAllExcept(layout);
                }
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                if (mode == SwipeMode.Multiple)
                    mOpenPositions.add(position);
                else {
                    closeAllExcept(layout);
                    mOpenPosition = position;
                }
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            public void setPosition(int position) {
                this.position = position;
            }
        }

    }

}
