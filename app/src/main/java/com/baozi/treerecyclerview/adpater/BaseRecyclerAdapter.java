package com.baozi.treerecyclerview.adpater;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.view.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通BaseRecyclerAdapter,itme无父子关系.
 * 限定泛型为BaseItem的子类.
 * 通过BaseItem去处理ViewHolder
 */
public class BaseRecyclerAdapter<T extends BaseItem> extends
        RecyclerView.Adapter<ViewHolder> {
    /**
     * items;
     */
    private List<T> mDatas;//展示数据
    private ItemManager<T> mItemManager;
    private CheckItem mCheckItem;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.createViewHolder(parent.getContext(), parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T t = mDatas.get(position);
        checkItemManage(t);
        t.onBindViewHolder(holder);
        onBindViewHolderClick(holder);
    }

    public void onBindViewHolderClick(final ViewHolder holder) {
        if (!holder.itemView.hasOnClickListeners()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    if (getCheckItem().checkItemPosition(layoutPosition)) {
                        int itemPosition = getCheckItem().getItemPosition(layoutPosition);
                        getDatas().get(itemPosition).onClick();
                    }
                }
            });
        }
    }

    public interface CheckItem {
        boolean checkItemPosition(int position);

        int getItemPosition(int position);
    }

    public CheckItem getCheckItem() {
        if (mCheckItem == null) {
            mCheckItem = new CheckItem() {
                @Override
                public boolean checkItemPosition(int position) {
                    return true;
                }

                @Override
                public int getItemPosition(int position) {
                    return position;
                }
            };
        }
        return mCheckItem;
    }

    public void setCheckItem(CheckItem checkItem) {
        mCheckItem = checkItem;
    }

    void checkItemManage(T item) {
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

    public void setDatas(List<T> datas) {
        if (datas != null) {
            mDatas = datas;
            getItemManager().notifyDataSetChanged();
        }
    }
}
