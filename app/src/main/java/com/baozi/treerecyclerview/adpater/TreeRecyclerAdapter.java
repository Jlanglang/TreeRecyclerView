package com.baozi.treerecyclerview.adpater;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;
import com.baozi.treerecyclerview.manager.ItemManageImpl;
import com.baozi.treerecyclerview.manager.ItemManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/4/20.
 * 树级结构recycleradapter.
 * item之间有子父级关系,
 */

public class TreeRecyclerAdapter extends BaseRecyclerAdapter<TreeItem> {

    private TreeRecyclerType type;

    private ItemManager<TreeItem> mItemManager;

    public TreeRecyclerAdapter() {
        this(null);
    }

    public TreeRecyclerAdapter(TreeRecyclerType treeRecyclerType) {
        type = treeRecyclerType == null ? TreeRecyclerType.SHOW_EXPAND : treeRecyclerType;
    }

    @Override
    public void onBindViewHolderClick(@NonNull final ViewHolder holder, View view) {
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    //获得处理后的position
                    layoutPosition = checkPosition(layoutPosition);
                    //拿到BaseItem
                    TreeItem item = getData(layoutPosition);
                    if (item == null) {
                        return;
                    }
                    TreeItemGroup itemParentItem = item.getParentItem();
                    //判断上一级是否需要拦截这次事件，只处理当前item的上级，不关心上上级如何处理.
                    if (itemParentItem != null && itemParentItem.onInterceptClick(item)) {
                        return;
                    }
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(holder, layoutPosition);
                    } else {
                        //拿到对应item,回调.
                        item.onClick(holder);
                    }
                }
//                }
            });
        }
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //获得holder的position
                int layoutPosition = holder.getLayoutPosition();
                //检查position是否可以点击
                //检查并得到真实的position
                int itemPosition = checkPosition(layoutPosition);
                if (mOnItemLongClickListener != null) {
                    return mOnItemLongClickListener.onItemLongClick(holder, itemPosition);
                }
                return false;
            }
        });
    }

    @Override
    public void setData(List<TreeItem> data) {
        if (null == data) {
            return;
        }
        getData().clear();
        assembleItems(data);
    }

    public void setData(TreeItemGroup treeItemGroup) {
        if (null == treeItemGroup) {
            return;
        }
        ArrayList<TreeItem> arrayList = new ArrayList<>();
        arrayList.add(treeItemGroup);
        setData(arrayList);
    }

    public final TreeRecyclerType getType() {
        return type;
    }

    /**
     * 对初始的一级items进行遍历,将每个item的childs拿出来,进行組合。
     *
     * @param items
     */
    private void assembleItems(List<TreeItem> items) {
        if (type != null) {
            List<TreeItem> datas = getData();
            datas.addAll(ItemHelperFactory.getChildItemsWithType(items, type));
        } else {
            super.setData(items);
        }
    }

    public ItemManager<TreeItem> getItemManager() {
        if (mItemManager == null) {
            mItemManager = new TreeItemManageImpl(this);
        }
        return mItemManager;
    }

    public void setItemManager(ItemManager<TreeItem> itemManage) {
        this.mItemManager = itemManage;
    }

    @Override
    public int getLayoutId(int position) {
        TreeItem data = getData(position);
        if (data != null) {
            return data.getLayoutId();
        }
        return 0;
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TreeItem t = getData(position);
        if (t == null) return;
        if (t instanceof TreeItemGroup) {
            ((TreeItemGroup) t).setCanExpand(type != TreeRecyclerType.SHOW_ALL);
        }
        checkItemManage(t);
        t.onBindViewHolder(holder);
    }

    private void checkItemManage(TreeItem item) {
        if (item.getItemManager() == null) {
            item.setItemManager(getItemManager());
        }
    }

    /**
     * 分割器
     */
    private final RecyclerView.ItemDecoration treeItemDecoration = new RecyclerView.ItemDecoration() {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int i = getItemCount();
            if (getItemCount() == 0) {
                return;
            }
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            int checkPosition = checkPosition(viewLayoutPosition);
            if (checkPosition < 0 || checkPosition >= i) {
                return;
            }
            TreeItem data = getData(checkPosition);
            if (data != null) {
                data.getItemOffsets(outRect, view, parent, state, checkPosition);
            }
        }
    };

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.removeItemDecoration(treeItemDecoration);
        recyclerView.addItemDecoration(treeItemDecoration);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            TreeSpanSizeLookup treeSpanSizeLookup = new TreeSpanSizeLookup(this, spanCount);
            gridLayoutManager.setSpanSizeLookup(treeSpanSizeLookup);
        }
    }


    @Override
    public int getItemSpanSize(int position, int maxSpan) {
        TreeItem data = getData(position);
        if (data == null) {
            return maxSpan;
        }
        return data.getSpanSize(maxSpan);
    }

    /**
     * 需要设置在setdata之前,否则type不会生效
     *
     * @param type
     */
    @Deprecated
    public void setType(TreeRecyclerType type) {
        this.type = type;
    }

    private class TreeItemManageImpl extends ItemManageImpl<TreeItem> {

        TreeItemManageImpl(BaseRecyclerAdapter<TreeItem> adapter) {
            super(adapter);
        }

        @Override
        public void addItem(TreeItem item) {
            if (item instanceof TreeItemGroup) {
                ArrayList<TreeItem> childItemsWithType = ItemHelperFactory.getChildItemsWithType((TreeItemGroup) item, type);
                childItemsWithType.add(0, item);
                super.addItems(childItemsWithType);
            } else {
                super.addItem(item);
            }
        }

        @Override
        public void addItems(List<TreeItem> items) {
            ArrayList<TreeItem> childItemsWithType = ItemHelperFactory.getChildItemsWithType(items, type);
            super.addItems(childItemsWithType);
        }

        @Override
        public void addItems(int position, List<TreeItem> items) {
            ArrayList<TreeItem> childItemsWithType = ItemHelperFactory.getChildItemsWithType(items, type);
            super.addItems(position, childItemsWithType);
        }

        @Override
        public void removeItem(TreeItem item) {
            if (item instanceof TreeItemGroup) {
                ArrayList<TreeItem> childItemsWithType = ItemHelperFactory.getChildItemsWithType((TreeItemGroup) item, type);
                childItemsWithType.add(0, item);
                super.removeItems(childItemsWithType);
            } else {
                super.removeItem(item);
            }
        }

        @Override
        public void removeItems(List<TreeItem> items) {
            ArrayList<TreeItem> childItemsWithType = ItemHelperFactory.getChildItemsWithType(items, type);
            super.removeItems(childItemsWithType);
        }

    }

    public final static class TreeSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        private final BaseRecyclerAdapter adapter;
        private final int spanCount;

        public TreeSpanSizeLookup(BaseRecyclerAdapter adapter, int spanCount) {
            this.adapter = adapter;
            this.spanCount = spanCount;
        }

        @Override
        public int getSpanSize(int position) {
            int i = adapter.getItemCount();
            if (i == 0) {
                return spanCount;
            }
            int itemToDataPosition = adapter.getItemManager().itemToDataPosition(position);
            if (itemToDataPosition < 0 || itemToDataPosition >= i) {
                return spanCount;
            }
            int itemSpanSize = adapter.getItemSpanSize(itemToDataPosition, spanCount);//新版本传入总数
            if (itemSpanSize == 0) {
                return spanCount;
            }
            return itemSpanSize;
        }
    }
}
