package com.baozi.treerecyclerview.adpater;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;
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
    public void onBindViewHolderClick(final ViewHolder holder, View view) {
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    //获得处理后的position
                    layoutPosition = checkPosition(layoutPosition);
                    //拿到BaseItem
                    TreeItem item = getDatas().get(layoutPosition);
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
        return getDatas().get(position).getLayoutId();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TreeItem t = getDatas().get(position);
        checkItemManage(t);
        t.onBindViewHolder(holder);
    }

    private void checkItemManage(TreeItem item) {
        if (item.getItemManager() == null) {
            item.setItemManager(getItemManager());
        }
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, TreeItem item, int position) {

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
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
                getData(checkPosition).getItemOffsets(outRect, layoutParams, checkPosition);
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
                    int checkPosition = checkPosition(position);
                    if (checkPosition < 0 || checkPosition >= i) {
                        return spanCount;
                    }
                    int itemSpanSize = getItemSpanSize(checkPosition);
                    if (itemSpanSize == 0) {
                        return spanCount;
                    }
                    return itemSpanSize;
                }
            });
        }
    }

    public int getItemSpanSize(int position) {
        return getData(position).getSpanSize();
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

    private class TreeItemManageImpl extends ItemManager<TreeItem> {

        TreeItemManageImpl(BaseRecyclerAdapter<TreeItem> adapter) {
            super(adapter);
        }

        @Override
        public void addItem(TreeItem item) {
            if (null == item) {
                return;
            }
            if (item instanceof TreeItemGroup) {
                ArrayList<TreeItem> childItemsWithType = ItemHelperFactory.getChildItemsWithType((TreeItemGroup) item, type);
                childItemsWithType.add(0, item);
                getDatas().addAll(childItemsWithType);
            } else {
                getDatas().add(item);
            }
            notifyDataChanged();
        }


        private ArrayList<TreeItem> checkItemHasItems(TreeItem item) {
            ArrayList<TreeItem> treeItems;
            if (item instanceof TreeItemGroup) {
                treeItems = ItemHelperFactory.getChildItemsWithType((TreeItemGroup) item, type);
                treeItems.add(0, item);
                return treeItems;
            } else {
                treeItems = new ArrayList<>();
                TreeItemGroup itemParentItem = item.getParentItem();
                if (itemParentItem != null) {
                    List child = itemParentItem.getChild();
                    if (child != null) {
                        int i = getDatas().indexOf(itemParentItem) + 1;
                        child.add(i, item);
                        treeItems.add(item);
//                        getDatas().add(i + child.size(), item);
                    } else {

                        child = new ArrayList();
                        itemParentItem.setChild(child);
                        treeItems.add(item);
//                        int i = getDatas().indexOf(itemParentItem);
//                        getDatas().add(i + child.size(), item);
                    }
                } else {
                    treeItems.add(item);
                }
            }
            return treeItems;
        }

        @Override
        public void addItem(int position, TreeItem item) {
            getDatas().add(position, item);
            notifyDataChanged();
        }

        @Override
        public void addItems(List<TreeItem> items) {
            ArrayList<TreeItem> childItemsWithType = ItemHelperFactory.getChildItemsWithType(items, type);
            getDatas().addAll(childItemsWithType);
            notifyDataChanged();
        }

        @Override
        public void addItems(int position, List<TreeItem> items) {
            ArrayList<TreeItem> childItemsWithType = ItemHelperFactory.getChildItemsWithType(items, type);
            getDatas().addAll(position, childItemsWithType);
            notifyDataChanged();
        }

        @Override
        public void removeItem(TreeItem item) {
            if (null == item) {
                return;
            }
            if (item instanceof TreeItemGroup) {
                ArrayList<TreeItem> childItemsWithType = ItemHelperFactory.getChildItemsWithType((TreeItemGroup) item, type);
                childItemsWithType.add(0, item);
                getDatas().removeAll(childItemsWithType);
            } else {
                getDatas().remove(item);
            }
            notifyDataChanged();
        }

        @Override
        public void removeItem(int position) {
            getDatas().remove(position);
            notifyDataChanged();
        }

        @Override
        public void removeItems(List<TreeItem> items) {
            ArrayList<TreeItem> childItemsWithType = ItemHelperFactory.getChildItemsWithType(items, type);
            getDatas().removeAll(childItemsWithType);
            notifyDataChanged();
        }

        @Override
        public void replaceItem(int position, TreeItem item) {
            getDatas().set(position, item);
            notifyDataChanged();
        }

        @Override
        public void replaceAllItem(List<TreeItem> items) {
            if (items != null) {
                setDatas(items);
                notifyDataChanged();
            }
        }

        @Override
        public void clean() {
            getDatas().clear();
            notifyDataChanged();
        }

        @Override
        public TreeItem getItem(int position) {
            return getDatas().get(position);
        }

        @Override
        public int getItemPosition(TreeItem item) {
            return getDatas().indexOf(item);
        }

    }

}
