//package com.baozi.treerecyclerview.adpater;
//
//import android.content.Context;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.baozi.treerecyclerview.view.BaseItem;
//import com.baozi.treerecyclerview.view.TreeItemGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TreeRecyclerViewAdapter<T extends BaseItem> extends RecyclerView.Adapter<ViewHolder> {
//    protected TreeRecyclerViewType type;
//    /**
//     * 上下文
//     */
//    protected Context mContext;
//    /**
//     * 存储原始的items;
//     */
//    private List<T> mDatas;//处理后的展示数据
//    /**
//     * 存储可见的items
//     */
//    protected List<T> mShowDatas;//处理后的展示数据
//
//    /**
//     * @param context 上下文
//     * @param datas   条目数据
//     */
//    public TreeRecyclerViewAdapter(Context context, List<T> datas) {
//        this(context, datas, TreeRecyclerViewType.SHOW_DEFUTAL);
//    }
//
//    /**
//     * @param context 上下文
//     * @param datas   条目数据
//     */
//    public TreeRecyclerViewAdapter(Context context, List<T> datas, TreeRecyclerViewType type) {
//        this.type = type;
//        mContext = context;
//        mDatas = datas == null ? new ArrayList<T>() : datas;
//        if (type == TreeRecyclerViewType.SHOW_ALL) {
//            mShowDatas = new ArrayList<>();
//            for (int i = 0; i < mDatas.size(); i++) {
//                T t = mDatas.get(i);
//                mShowDatas.add(t);
//                if (t instanceof TreeItemGroup) {
//                    List allChilds = ((TreeItemGroup) t).getChilds(type);
//                    mShowDatas.addAll(allChilds);
//                }
//            }
//        } else {
//            mShowDatas = mDatas;
//        }
//    }
//
//    /**
//     * 相应RecyclerView的点击事件 展开或关闭某节点
//     *
//     * @param position 触发的条目
//     */
//    private void expandOrCollapse(int position) {
//        BaseItem baseItem = mShowDatas.get(position);
//        if (baseItem instanceof TreeItemGroup && ((TreeItemGroup) baseItem).isCanChangeExpand()) {
//            TreeItemGroup treeParentItem = (TreeItemGroup) baseItem;
//            boolean expand = treeParentItem.isExpand();
//            List allChilds = treeParentItem.getChilds(type);
//            if (expand) {
//                mShowDatas.removeAll(allChilds);
//                treeParentItem.onCollapse();
//                treeParentItem.setExpand(false);
//            } else {
//                mShowDatas.addAll(position + 1, allChilds);
//                treeParentItem.onExpand();
//                treeParentItem.setExpand(true);
//            }
//            notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//        if (layoutManager instanceof GridLayoutManager) {
//            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
//            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    BaseItem baseItem = mShowDatas.get(position);
//                    if (baseItem.getSpanSize() == 0) {
//                        return gridLayoutManager.getSpanCount();
//                    }
//                    return baseItem.getSpanSize();
//                }
//            });
//        }
//    }
//
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewHolder viewHolder = ViewHolder.createViewHolder(mContext, parent, viewType);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        final BaseItem baseItem = mShowDatas.get(position);
//        baseItem.onBindViewHolder(holder);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (type != TreeRecyclerViewType.SHOW_ALL) {
//                    expandOrCollapse(holder.getLayoutPosition());
//                } else {
//                    baseItem.onClickChange();
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return mShowDatas.get(position).getLayoutId();
//    }
//
//    @Override
//    public int getItemCount() {
//        return mShowDatas == null ? 0 : mShowDatas.size();
//    }
//
//    public List<T> getDatas() {
//        return mDatas;
//    }
//
//    public void setDatas(List<T> datas) {
//        mDatas = datas;
//    }
//
//}