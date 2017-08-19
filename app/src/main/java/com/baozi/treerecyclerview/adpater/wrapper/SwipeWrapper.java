package com.baozi.treerecyclerview.adpater.wrapper;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.adpater.wrapper.swipe.SwipeItemMangerInterface;
import com.baozi.treerecyclerview.adpater.wrapper.swipe.SwipeLayout;
import com.baozi.treerecyclerview.adpater.wrapper.swipe.SwipeMode;
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;

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

    private SwipeItemMangerInterface mSwipeManger;
    private HashMap<ViewHolder, SwipeLayout> swipeLayoutHashMap = new HashMap<>();
    private SparseArray<SwipeItem> swipeItemSparseArray = new SparseArray<>();

    public SwipeWrapper(BaseRecyclerAdapter adapter) {
        super(adapter);
    }

    public void updateSwipeDatas(List<Object> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof SwipeItem) {
                swipeItemSparseArray.put(i, (SwipeItem) list.get(i));
            }
        }
    }

    @Override
    public void setDatas(List datas) {
        super.setDatas(datas);
        updateSwipeDatas(datas);
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
        if (viewType == -666) {
            ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
            viewHolder.itemView.setOnClickListener(null);

            SwipeLayout swipeLayout = new SwipeLayout(parent.getContext());
            swipeLayout.setLayoutParams(new SwipeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            swipeLayout.addView(viewHolder.itemView);
            ViewHolder swipeViewHolder = ViewHolder.createViewHolder(swipeLayout);
            swipeLayoutHashMap.put(swipeViewHolder, swipeLayout);
            onBindViewHolderClick(swipeViewHolder);
            return swipeViewHolder;
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return swipeItemSparseArray.get(position) == null ? super.getItemViewType(position) : -666;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object data = getData(position);
        if (data instanceof SwipeItem) {
            SwipeItem swipeItem = (SwipeItem) data;
            SwipeLayout swipeLayout = (SwipeLayout) holder.itemView;
            checkSwipeLayout(holder, swipeItem);
            getSwipeManger().bind(swipeLayout, swipeItem.getSwipeLayoutId(), position);
        }
        super.onBindViewHolder(holder, position);
    }

    private void checkSwipeLayout(ViewHolder holder, SwipeItem data) {
        SwipeLayout view = swipeLayoutHashMap.get(holder);
        Map<SwipeLayout.DragEdge, View> dragEdgeMap = view.getDragEdgeMap();
        if (dragEdgeMap.get(data.getDragEdge()) == null) {
            View inflate = LayoutInflater.from(view.getContext()).inflate(data.getSwipeLayoutId(), null);
            view.addDrag(data.getDragEdge(), inflate, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
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
     * SwipeItemMangerImpl is a helper class to help all the adapters to maintain open status.
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
//                if (isOpen(position)) {
//                    v.open(false,false);
//                } else {
//                    v.close(false,false);
//                }
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
