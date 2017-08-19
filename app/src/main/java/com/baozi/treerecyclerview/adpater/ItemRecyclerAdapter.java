package com.baozi.treerecyclerview.adpater;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;

/**
 * 普通BaseRecyclerAdapter,itme无父子关系.
 * 限定泛型为BaseItem的子类.
 * 通过BaseItem去处理ViewHolder
 */
public class ItemRecyclerAdapter<T extends BaseItem> extends
        BaseRecyclerAdapter<T> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //看源码,这里的parent就是Recyclerview,所以不会为null.可以通过它拿到context
        ViewHolder viewHolder = ViewHolder.createViewHolder(parent, viewType);
        onBindViewHolderClick(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T t = getDatas().get(position);
        checkItemManage(t);
        t.onBindViewHolder(holder);
    }

    @Override
    public int getLayoutId(int position) {
        return getDatas().get(position).getLayoutId();
    }

    @Override
    public void onBind(ViewHolder holder, T t, int position) {

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
                            return mOnItemLongClickListener.onItemLongClick(holder, itemPosition);
                        }
                    }
                    return false;
                }
            });
        }
    }


//    public interface OnItemClickLitener {
//        void onItemClick(ViewHolder viewHolder, BaseItem baseItem, int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickLitener onItemClickListener) {
//        mOnItemClickListener = onItemClickListener;
//    }
//
//
//    public interface OnItemLongClickListener {
//        boolean onItemLongClick(ViewHolder viewHolder, BaseItem baseItem, int position);
//    }
//
//    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
//        mOnItemLongClickListener = onItemLongClickListener;
//    }


    private void checkItemManage(T item) {
        if (item.getItemManager() == null) {
            item.setItemManager(getItemManager());
        }
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

}
