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
        type = treeRecyclerType == null ? TreeRecyclerType.SHOW_DEFAULT : treeRecyclerType;
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
                    TreeItemGroup itemParentItem = item.getParentItem();
                    //判断上一级是否需要拦截这次事件，只处理当前item的上级，不关心上上级如何处理.
                    if (itemParentItem != null && itemParentItem.onInterceptClick(item)) {
                        return;
                    }
                    //必须是TreeItemGroup才能展开折叠,并且type不能为 TreeRecyclerType.SHOW_ALL
                    if (item instanceof TreeItemGroup && type != TreeRecyclerType.SHOW_ALL) {
                        TreeItemGroup treeItemGroup = (TreeItemGroup) item;
                        treeItemGroup.setExpand(!treeItemGroup.isExpand());
                    }
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(holder, layoutPosition);
                    } else {
                        //拿到对应item,回调.
                        getDatas().get(layoutPosition).onClick(holder);
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
    public void setDatas(List<TreeItem> items) {
        if (null == items || items.isEmpty()) {
            return;
        }
        getDatas().clear();
        assembleItems(items);
    }

    public void setDatas(TreeItemGroup treeItemGroup) {
        if (null == treeItemGroup) {
            return;
        }
        ArrayList<TreeItem> arrayList = new ArrayList<>();
        arrayList.add(treeItemGroup);
        setDatas(arrayList);
    }

    /**
     * 对初始的一级items进行遍历,将每个item的childs拿出来,进行組合。
     *
     * @param items
     */
    private void assembleItems(List<TreeItem> items) {
        if (type != null) {
            List<TreeItem> datas = getDatas();
            datas.addAll(ItemHelperFactory.getChildItemsWithType(items, type));
        } else {
            super.setDatas(items);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TreeItem t = getData(position);
        if (t == null) return;
        checkItemManage(t);
        t.onBindViewHolder(holder);
    }

    private void checkItemManage(TreeItem item) {
        if (item.getItemManager() == null) {
            item.setItemManager(getItemManager());
        }
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                int viewLayoutPosition = layoutParams.getViewLayoutPosition();
                int i = getItemCount();
                if (getItemCount() == 0) {
                    return;
                }
                int checkPosition = checkPosition(viewLayoutPosition);
                if (checkPosition < 0 || checkPosition >= i) {
                    return;
                }
                TreeItem data = getData(checkPosition);
                if (data != null) {
                    data.getItemOffsets(outRect, layoutParams, checkPosition);
                }
            }
        });
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final int spanCount = gridLayoutManager.getSpanCount();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int i = getItemCount();
                    if (i == 0) {
                        return spanCount;
                    }
                    int itemToDataPosition = getItemManager().itemToDataPosition(position);
                    if (itemToDataPosition < 0 || itemToDataPosition >= i) {
                        return spanCount;
                    }
                    int itemSpanSize = getItemSpanSize(itemToDataPosition);
                    if (itemSpanSize == 0) {
                        return spanCount;
                    }
                    return itemSpanSize;
                }

                @Override
                public int getSpanIndex(int position, int spanCount) {
                    return super.getSpanIndex(position, spanCount);
                }

                @Override
                public int getSpanGroupIndex(int adapterPosition, int spanCount) {
                    return super.getSpanGroupIndex(adapterPosition, spanCount);
                }
            });
        }
    }

    public int getItemSpanSize(int position) {
        TreeItem data = getData(position);
        if (data == null) {
            return 0;
        }
        return data.getSpanSize();
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
//                getDatas().removeAll(childItemsWithType);
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

}
