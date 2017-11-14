package com.baozi.treerecyclerview.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.manager.ItemManageImpl;
import com.baozi.treerecyclerview.manager.ItemManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    protected ItemManager<T> mItemManager;
    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;
    private List<T> mDatas;
    private CheckItem mCheckItem;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.createViewHolder(parent, viewType);
        onBindViewHolderClick(holder, holder.itemView);
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBindViewHolder(holder, getDatas().get(position), position);
    }

    /**
     * 实现item的点击事件
     */
    public void onBindViewHolderClick(final ViewHolder viewHolder, View view) {
        //判断当前holder是否已经设置了点击事件
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获得holder的position
                    int layoutPosition = viewHolder.getLayoutPosition();
                    //检查item的position,是否可以点击.
//                    if (getCheckItem().checkClick(layoutPosition)) {
//                    检查并得到真实的position
                    int itemPosition = getCheckItem().checkPosition(layoutPosition);
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(viewHolder, itemPosition);
                    }
//                    }
                }
            });
        }
        if (!view.isLongClickable()) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //获得holder的position
                    int layoutPosition = viewHolder.getLayoutPosition();
                    //检查position是否可以点击
//                    if (getCheckItem().checkClick(layoutPosition)) {
                    //检查并得到真实的position
                    int itemPosition = getCheckItem().checkPosition(layoutPosition);
                    if (mOnItemLongClickListener != null) {
                        return mOnItemLongClickListener.onItemLongClick(viewHolder, itemPosition);
                    }
//                    }
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId(position);
    }

    @Override
    public int getItemCount() {
        return getDatas().size();
    }

    public int getItemSpanSize(int position) {
        return 0;
    }

    /**
     * 默认实现的CheckItem接口
     *
     * @return
     */
    public CheckItem getCheckItem() {
        if (mCheckItem == null) {
            mCheckItem = new CheckItem() {
//                @Override
//                public int checkCount() {
//                    return getItemCount();
//                }
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
        if (position >= 0) {
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
            mItemManager = new ItemManageImpl<T>(this);
        }
        return mItemManager;
    }

    public void setItemManager(ItemManager<T> itemManager) {
        mItemManager = itemManager;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(ViewHolder viewHolder, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(ViewHolder viewHolder, int position);
    }

    /**
     * 检查item的position,主要viewholder的getLayoutPosition不一定是需要的.
     */
    public abstract class CheckItem {

//        //检查当前position,是否可点击
//        public boolean checkClick(int position) {
//            return true;
//        }

        //检查当前position,获取原始角标
        public int checkPosition(int position) {
            return position;
        }

//        //检查总条目数,获取实际数据长度
//        public abstract int checkCount();

    }


    /**
     * 获取该position的item的layout
     *
     * @param position 角标
     * @return item的layout id
     */
    public abstract int getLayoutId(int position);

    /**
     * view与数据绑定
     *
     * @param holder
     * @param t
     * @param position
     */
    public abstract void onBindViewHolder(ViewHolder holder, T t, int position);

}
