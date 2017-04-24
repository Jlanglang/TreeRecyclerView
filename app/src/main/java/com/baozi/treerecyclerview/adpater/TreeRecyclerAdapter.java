package com.baozi.treerecyclerview.adpater;

import android.support.annotation.IntDef;
import android.support.annotation.RestrictTo;
import android.support.design.widget.AppBarLayout;
import android.view.View;

import com.baozi.treerecyclerview.view.BaseItem;
import com.baozi.treerecyclerview.view.ItemGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Created by baozi on 2017/4/20.
 */

public class TreeRecyclerAdapter<T extends BaseItem> extends BaseRecyclerAdapter<T> {
    private TreeRecyclerViewType type;
    /**
     * 最初的数据
     */
    private List<T> initialDatas;

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        T t = getDatas().get(position);
        t.onBindViewHolder(holder);
        if (!holder.itemView.hasOnClickListeners()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    if (type != TreeRecyclerViewType.SHOW_ALL) {
                        expandOrCollapse(layoutPosition);
                    } else {
                        getDatas().get(layoutPosition).onClick(layoutPosition);
                    }
                }
            });
        }
    }

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
                if (t instanceof ItemGroup) {
                    List allChilds = ((ItemGroup) t).getChilds(type);
                    getDatas().addAll(allChilds);
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
        if (baseItem instanceof ItemGroup && ((ItemGroup) baseItem).isCanChangeExpand()) {
            ItemGroup treeParentItem = (ItemGroup) baseItem;
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
}
