package com.baozi.treerecyclerview.adpater;

import android.view.View;

import com.baozi.treerecyclerview.view.BaseItem;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;
import com.baozi.treerecyclerview.view.TreeItemManager;
import com.baozi.treerecyclerview.view.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/4/20.
 */

public class TreeRecyclerAdapter<T extends TreeItem> extends BaseRecyclerAdapter<T> {
    private TreeRecyclerViewType type;
    private TreeItemManager<T> mTreeItemManager;
    /**
     * 最初的数据
     */
    private List<T> initialDatas;

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        T item = getDatas().get(position);
        item.onBindViewHolder(holder);
        if (!holder.itemView.hasOnClickListeners()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    if (type != TreeRecyclerViewType.SHOW_ALL) {
                        expandOrCollapse(layoutPosition);
                    } else {
                        T item = getDatas().get(layoutPosition);
                        item.onClick();
                    }
                }
            });
        }
        if (item.getTreeItemManager() == null) {
            item.setTreeItemManager(getTreeItemManager());
        }
    }

    /**
     * 获得初始的data
     *
     * @return
     */
    public List<T> getInitialDatas() {
        if (initialDatas == null) {
            initialDatas = new ArrayList<>();
        }
        return initialDatas;
    }

    @Override
    public void setDatas(List<T> datas) {
        initialDatas = datas;
        if (type == TreeRecyclerViewType.SHOW_ALL) {
            for (int i = 0; i < datas.size(); i++) {
                T t = datas.get(i);
                getDatas().add(t);
                if (t instanceof TreeItemGroup) {
                    getDatas().addAll(((TreeItemGroup) t).getChilds(type));
                }
            }
        } else {
            super.setDatas(datas);
        }
    }

    /**
     * 相应RecyclerView的点击事件 展开或关闭某节点
     *
     * @param position 触发的条目
     */
    private void expandOrCollapse(int position) {
        BaseItem baseItem = getDatas().get(position);
        if (baseItem instanceof TreeItemGroup && ((TreeItemGroup) baseItem).isCanChangeExpand()) {
            TreeItemGroup treeParentItem = (TreeItemGroup) baseItem;
            boolean expand = treeParentItem.isExpand();
            List<T> allChilds = treeParentItem.getChilds(type);
            if (expand) {
                getDatas().removeAll(allChilds);
                treeParentItem.onCollapse();
                treeParentItem.setExpand(false);
            } else {
                getDatas().addAll(position + 1, allChilds);
                treeParentItem.onExpand();
                treeParentItem.setExpand(true);
            }
            notifyDataSetChanged();
        }
    }

    public void setType(TreeRecyclerViewType type) {
        this.type = type;
    }

    /**
     * 操作adapter
     *
     * @return
     */
    public TreeItemManager<T> getTreeItemManager() {
        if (mTreeItemManager == null) {
            mTreeItemManager = new TreeItemManager<T>() {
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
                    TreeRecyclerAdapter.this.notifyDataSetChanged();
                }
            };
        }
        return mTreeItemManager;
    }

    public void setTreeItemManager(TreeItemManager<T> treeItemManager) {
        mTreeItemManager = treeItemManager;
    }
}
