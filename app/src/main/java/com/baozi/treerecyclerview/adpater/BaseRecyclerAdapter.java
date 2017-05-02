package com.baozi.treerecyclerview.adpater;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

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
    private ItemManager<T> mItemManager;
    private CheckItem mCheckItem;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //看源码,这里的parent就是Recyclerview,所以不会为null.可以通过它拿到context
        return ViewHolder.createViewHolder(parent.getContext(), parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T t = getDatas().get(position);
        checkItemManage(t);
        t.onBindViewHolder(holder);
        onBindViewHolderClick(holder);
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
                    //检查position是否可以点击
                    if (getCheckItem().checkPosition(layoutPosition)) {
                        //检查并得到真实的position
                        int itemPosition = getCheckItem().getAfterCheckingPosition(layoutPosition);
                        //拿到对应item,回调.
                        getDatas().get(itemPosition).onClick();
                    }
                }
            });
        }
    }

    /**
     * 检出item的position,主要viewholder的getLayoutPosition不一定是需要的.
     * 比如添加了headview和footview.
     */
    public interface CheckItem {
        boolean checkPosition(int position);

        int getAfterCheckingPosition(int position);
    }

    /**
     * 默认实现的CheckItem接口
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
            mItemManager = new ItemManager<T>() {
                @Override
                public void addTreeItem(T item) {
                    getDatas().add(item);
                    notifyDataSetChanged();
                }

                @Override
                public void addTreeItem(List<T> items) {
                    getDatas().addAll(items);
                    notifyDataSetChanged();
                }

                @Override
                public void removeItem(T item) {
                    getDatas().remove(item);
                    notifyDataSetChanged();
                }

                @Override
                public void removeItem(List<T> items) {
                    getDatas().removeAll(items);
                    notifyDataSetChanged();
                }

                @Override
                public void notifyItemChanged(int position) {
                }

                @Override
                public void notifyItemInserted(int position) {
                }

                @Override
                public void notifyItemRemoved(int position) {
                }

                @Override
                public void notifyItemRangeChanged(int positionStart, int itemCount) {
                }

                @Override
                public void notifyItemRangeInserted(int positionStart, int itemCount) {
                }

                @Override
                public void notifyItemRangeRemoved(int positionStart, int itemCount) {
                }

                @Override
                public void notifyDataSetChanged() {
                    BaseRecyclerAdapter.this.notifyDataSetChanged();
                }
            };
        }
        return mItemManager;
    }

    public void setItemManager(ItemManager<T> itemManager) {
        mItemManager = itemManager;
    }

    /**
     * 这里将LayoutId作为type,因为LayoutId不可能相同,个人觉的可以作为item的标志
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
                    BaseItem baseItem = mDatas.get(position);
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
        return mDatas == null ? 0 : mDatas.size();
    }

    public List<T> getDatas() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        return mDatas;
    }

    /**
     * 需要手动setDatas(List<T> datas),否则数据为空
     * @param datas
     */
    public void setDatas(List<T> datas) {
        if (datas != null) {
            mDatas = datas;
            getItemManager().notifyDataSetChanged();
        }
    }
}
