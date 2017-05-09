package com.baozi.treerecyclerview.adpater;

import android.view.View;

import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.view.TreeItem;
import com.baozi.treerecyclerview.view.TreeItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/4/20.
 * 树级结构recycleradapter.
 * item之间有子父级关系,
 */

public class TreeRecyclerAdapter<T extends TreeItem> extends BaseRecyclerAdapter<T> {

    private TreeRecyclerViewType type;

    private ItemManager<T> mItemManager;
    /**
     * 最初的数据.没有经过增删操作.
     */
    private List<T> initialDatas;


    @Override
    public void onBindViewHolderClick(final ViewHolder holder) {
        if (!holder.itemView.hasOnClickListeners()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    if (getCheckItem().checkPosition(layoutPosition)) {
                        int itemPosition = getCheckItem().getAfterCheckingPosition(layoutPosition);
                        //展开,折叠和item点击不应该同时响应事件.
                        if (type != TreeRecyclerViewType.SHOW_ALL) {
                            //展开,折叠
                            expandOrCollapse(itemPosition);
                        } else {
                            //点击事件
                            T item = getDatas().get(itemPosition);
                            TreeItemGroup itemParentItem = item.getParentItem();
                            //判断上一级是否需要拦截这次事件，只处理当前item的上级，不关心上上级如何处理.
                            if (itemParentItem != null && itemParentItem.onInterceptClick(item)) {
                                return;
                            }
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick(holder, getData(itemPosition), itemPosition);
                            } else {
                                //拿到对应item,回调.
                                getDatas().get(itemPosition).onClick();
                            }
                        }
                    }
                }
            });
        }
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

    /**
     * 获得初始的data
     *
     * @return
     */
    public List<T> getInitialDatas() {
        if (initialDatas == null) {
            initialDatas = getDatas();
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
                    List childs = ((TreeItemGroup) t).getAllChilds();
                    if (childs != null) {
                        getDatas().addAll(childs);
                    }
                }
            }
        } else {
            super.setDatas(datas);
        }
    }


    public ItemManager<T> getItemManager() {
        if (mItemManager == null) {
            return new TreeItemManageImpl();
        }
        return mItemManager;
    }

    public void setItemManager(ItemManager itemManage) {
        this.mItemManager = itemManage;
    }

    /**
     * 相应RecyclerView的点击事件 展开或关闭某节点
     *
     * @param position 触发的条目
     */
    private void expandOrCollapse(int position) {
        T baseItem = getDatas().get(position);
        if (baseItem instanceof TreeItemGroup && ((TreeItemGroup) baseItem).isCanChangeExpand()) {
            TreeItemGroup treeParentItem = (TreeItemGroup) baseItem;
            boolean expand = treeParentItem.isExpand();
//            if (expand) {
//                //如果展开状态就折叠.
//                treeParentItem.onCollapse();
//            } else {
//                //如果折叠状态就展开.
//                treeParentItem.onExpand();
//            }
            treeParentItem.setExpand(!expand);
        }
    }

    /**
     * 需要设置在setdata之前,否则type不会生效
     *
     * @param type
     */
    public void setType(TreeRecyclerViewType type) {
        this.type = type;
    }

    private class TreeItemManageImpl implements ItemManager<T> {
        @Override
        public void addItem(T item) {
            if (null == item) {
                return;
            }
            if (item instanceof TreeItemGroup) {
                getDatas().add(item);
            } else {
                TreeItemGroup itemParentItem = item.getParentItem();
                if (itemParentItem != null) {
                    List childs = itemParentItem.getChilds();
                    if (childs != null) {
                        int i = getDatas().indexOf(itemParentItem);
                        getDatas().add(i + itemParentItem.getChilds().size(), item);
                    } else {
                        childs = new ArrayList();
                    }
                    childs.add(item);
                }
            }
            notifyDataChanged();
        }

        @Override
        public void addItem(int position, T item) {
            getDatas().add(position, item);
            if (item != null && item.getParentItem() != null) {
                item.getParentItem().getChilds().add(item);
            }
            notifyDataChanged();
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
            if (null == item) {
                return;
            }
            getDatas().remove(item);

            TreeItemGroup itemParentItem = item.getParentItem();
            if (itemParentItem != null) {
                List childs = itemParentItem.getChilds();
                if (childs != null) {
                    childs.remove(item);
                }
            }
            notifyDataChanged();
        }

        @Override
        public void removeItem(int position) {
            T t = getDatas().get(position);
            TreeItemGroup parentItem = t.getParentItem();
            if (parentItem != null && parentItem.getChilds() != null) {
                parentItem.getChilds().remove(t);
            }
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
            T t = getDatas().get(position);
            if (t instanceof TreeItemGroup) {
                getDatas().set(position, item);
            } else {
                TreeItemGroup parentItem = t.getParentItem();
                if (parentItem != null && parentItem.getChilds() != null) {
                    List childs = parentItem.getChilds();
                    int i = childs.indexOf(t);
                    childs.set(i, item);
                }
                getDatas().set(position, item);
            }
            notifyDataChanged();
        }

        @Override
        public T getItem(int position) {
            return getDatas().get(position);
        }

        @Override
        public void notifyDataChanged() {
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(T item) {
            return getDatas().indexOf(item);
        }
    }
}
