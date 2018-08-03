package com.baozi.treerecyclerview.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.createViewHolder(parent, viewType);
        onBindViewHolderClick(holder, holder.itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onBindViewHolder(holder, getDatas().get(position), position);
    }

    /**
     * 实现item的点击事件
     */
    public void onBindViewHolderClick(@NonNull final ViewHolder viewHolder, View view) {
        //判断当前holder是否已经设置了点击事件
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获得holder的position
                    int layoutPosition = viewHolder.getLayoutPosition();
                    //检查item的position,是否可以点击.
//                    检查并得到真实的position
                    int itemPosition = checkPosition(layoutPosition);
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(viewHolder, layoutPosition);
                    }
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
                    //检查并得到真实的position
                    int itemPosition = checkPosition(layoutPosition);
                    if (mOnItemLongClickListener != null) {
                        return mOnItemLongClickListener.onItemLongClick(viewHolder, layoutPosition);
                    }
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

    public List<T> getDatas() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        return mDatas;
    }

    public void setDatas(List<T> datas) {
        if (datas != null && !datas.isEmpty()) {
            getDatas().clear();
            getDatas().addAll(datas);
        }
    }

    @Nullable
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
        void onItemClick(@NonNull ViewHolder viewHolder, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(@NonNull ViewHolder viewHolder, int position);
    }

//    /**
//     * 检查item属性
//     */
//    public interface CheckItemInterface {
//        int checkPosition(int position);
//    }
//
//    public void removeCheckItemInterfaces(CheckItemInterface itemInterface) {
//        if (checkItemInterfaces == null) return;
//        checkItemInterfaces.remove(itemInterface);
//    }
//
//    public void addCheckItemInterfaces(CheckItemInterface itemInterface) {
//        if (checkItemInterfaces == null) {
//            checkItemInterfaces = new ArrayList<>();
//        }
//        checkItemInterfaces.add(itemInterface);
//    }

//    private ArrayList<CheckItemInterface> checkItemInterfaces;

    //检查当前position,获取原始角标
    public int checkPosition(int position) {
//        if (checkItemInterfaces != null) {
//            for (CheckItemInterface itemInterface : checkItemInterfaces) {
//                position = itemInterface.checkPosition(position);
//            }
//        }
        return position;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, T t, int position) {

    }

    public void clear() {
        getDatas().clear();
    }
}
