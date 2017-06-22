package com.baozi.treerecyclerview.adpater;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.R;
import com.baozi.treerecyclerview.base.BaseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通BaseRecyclerAdapter,itme无父子关系.
 * 限定泛型为BaseItem的子类.
 * 通过BaseItem去处理ViewHolder
 */
public class BaseRecyclerAdapter<T extends BaseItem> extends
        RecyclerView.Adapter<ViewHolder> {

    private List<T> mDatas;//展示数据
    private CheckItem mCheckItem;

    protected ItemManager<T> mItemManager;
    protected OnItemClickLitener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //看源码,这里的parent就是Recyclerview,所以不会为null.可以通过它拿到context
        ViewHolder viewHolder = ViewHolder.createViewHolder(parent.getContext(), parent, viewType);
        onBindViewHolderClick(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T t = getDatas().get(position);
        checkItemManage(t);
        t.onBindViewHolder(holder);
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
                            mOnItemClickListener.onItemClick(holder, getData(itemPosition), itemPosition);
                        } else {
                            //拿到对应item,回调.
                            getDatas().get(itemPosition).onClick();
                        }
                    }
                }
            });
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
                            return mOnItemLongClickListener.onItemLongClick(holder, getData(itemPosition), itemPosition);
                        }
                    }
                    return false;
                }
            });
        }
    }

    /**
     * 检查item的position,主要viewholder的getLayoutPosition不一定是需要的.
     * 比如添加了headview和footview.
     */
    public interface CheckItem {
        boolean checkPosition(int position);

        int getAfterCheckingPosition(int position);
    }

    public interface OnItemClickLitener {
        void onItemClick(ViewHolder viewHolder, BaseItem baseItem, int position);
    }

    public void setOnItemClickListener(OnItemClickLitener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    public interface OnItemLongClickListener {
        boolean onItemLongClick(ViewHolder viewHolder, BaseItem baseItem, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
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
        mCheckItem = checkItem;
    }

    private void checkItemManage(T item) {
        if (item.getItemManager() == null) {
            item.setItemManager(getItemManager());
        }
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

    /**
     * 这里将LayoutId作为type,因为LayoutId不可能相同,个人觉的可以作为item的标志
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getLayoutId();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    BaseItem baseItem = getDatas().get(position);
                    if (baseItem.getSpanSize() == 0) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return baseItem.getSpanSize();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return getDatas() == null ? 0 : getDatas().size();
    }

    public List<T> getDatas() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        return mDatas;
    }

    public T getData(int position) {
        if (position < getDatas().size()) {
            return getDatas().get(position);
        }
        return null;
    }

    /**
     * 需要手动setDatas(List<T> datas),否则数据为空
     * 该方法不会主动刷新布局.如需替换datas并刷新,可以getItemManager().replaceAllItem(List list);
     *
     * @param datas 需要修改的数据
     */
    public void setDatas(@Nullable List<T> datas) {
        if (datas != null) {
            getDatas().clear();
            getDatas().addAll(datas);
        }
    }

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
