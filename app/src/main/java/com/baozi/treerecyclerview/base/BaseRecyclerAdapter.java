package com.baozi.treerecyclerview.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.adpater.ItemManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private List<T> mDatas;
    private CheckItem mCheckItem;
    protected ItemManager<T> mItemManager;
    protected OnItemClickLitener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.createViewHolder(parent, viewType);
        onBindViewHolderClick(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBind(holder, getDatas().get(position), position);
    }

    /**
     * 实现item的点击事件
     *
     * @param holder 绑定点击事件的ViewHolder
     */
    public void onBindViewHolderClick(final ViewHolder holder) {
        //判断当前holder是否已经设置了点击事件
        if (!holder.itemView.hasOnClickListeners()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获得holder的position
                    int layoutPosition = holder.getLayoutPosition();
                    //检查item的position,是否可以点击.
                    if (getCheckItem().checkPosition(layoutPosition)) {
                        //检查并得到真实的position
                        int itemPosition = getCheckItem().getAfterCheckingPosition(layoutPosition);
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(holder, itemPosition);
                        }
                    }
                }
            });
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //获得holder的position
                int layoutPosition = holder.getLayoutPosition();
                //检查position是否可以点击
                if (getCheckItem().checkPosition(layoutPosition)) {
                    //检查并得到真实的position
                    int itemPosition = getCheckItem().getAfterCheckingPosition(layoutPosition);
                    if (mOnItemLongClickListener != null) {
                        return mOnItemLongClickListener.onItemLongClick(holder, itemPosition);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId(position);
    }

    @Override
    public int getItemCount() {
        return getDatas().size();
    }

    /**
     * 默认实现的CheckItem接口
     *
     * @return
     */
    public CheckItem getCheckItem() {
        if (mCheckItem == null) {
            mCheckItem = new CheckItem() {
                @Override
                public boolean checkPosition(int position) {
                    return true;
                }

                @Override
                public int getAfterCheckingPosition(int position) {
                    return position;
                }
            };
        }
        return mCheckItem;
    }

    public void setCheckItem(CheckItem checkItem) {
        this.mCheckItem = checkItem;
    }

    public List<T> getDatas() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        return mDatas;
    }

    public void setDatas(List<T> datas) {
        if (datas != null) {
            getDatas().clear();
            getDatas().addAll(datas);
        }
    }

    public T getData(int position) {
        if (position >= 0 && position < getDatas().size()) {
            return getDatas().get(position);
        }
        return null;
    }

    /**
     * 操作adapter
     *
     * @return
     */
    public ItemManager<T> getItemManager() {
        if (mItemManager == null) {
            mItemManager = new ItemManageImpl(this);
        }
        return mItemManager;
    }

    public void setItemManager(ItemManager<T> itemManager) {
        mItemManager = itemManager;
    }

    public void setOnItemClickListener(OnItemClickLitener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickLitener {
        void onItemClick(ViewHolder viewHolder, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(ViewHolder viewHolder, int position);
    }

    /**
     * 检查item的position,主要viewholder的getLayoutPosition不一定是需要的.
     * 比如添加了headview和footview.
     */
    public interface CheckItem {

        boolean checkPosition(int position);

        int getAfterCheckingPosition(int position);

    }

    public abstract int getLayoutId(int position);

    public abstract void onBind(ViewHolder holder, T t, int position);

    /**
     * 默认使用 notifyDataChanged();刷新.
     * 如果使用带动画效果的,条目过多可能会出现卡顿.
     */
    private class ItemManageImpl extends ItemManager<T> {

        public ItemManageImpl(BaseRecyclerAdapter<T> adapter) {
            super(adapter);
        }

        @Override
        public void addItem(T item) {
            getDatas().add(item);
            notifyDataChanged();
        }

        @Override
        public void addItem(int position, T item) {
            getDatas().add(position, item);
            notifyDataChanged();
        }

        @Override
        public void addItems(List<T> items) {
            getDatas().addAll(items);
            notifyDataChanged();
        }

        @Override
        public void addItems(int position, List<T> items) {
            getDatas().addAll(position, items);
            notifyDataChanged();
        }

        @Override
        public void removeItem(T item) {
            getDatas().remove(item);
            notifyDataChanged();
        }

        @Override
        public void removeItem(int position) {
            getDatas().remove(position);
            notifyDataChanged();
        }

        @Override
        public void removeItems(List<T> items) {
            getDatas().removeAll(items);
            notifyDataChanged();
        }

        @Override
        public void replaceItem(int position, T item) {
            getDatas().set(position, item);
            notifyDataChanged();
        }

        @Override
        public void replaceAllItem(List<T> items) {
            if (items != null) {
                setDatas(items);
                notifyDataChanged();
            }
        }

        @Override
        public T getItem(int position) {
            return getDatas().get(position);
        }

        @Override
        public int getItemPosition(T item) {
            return getDatas().indexOf(item);
        }
    }
}
