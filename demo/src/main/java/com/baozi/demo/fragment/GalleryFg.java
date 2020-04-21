package com.baozi.demo.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.item.SimpleTreeItem;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class GalleryFg extends SimpleRecyclerViewFg<TreeRecyclerAdapter> {
    private LinearSnapHelper snapHelper = new LinearSnapHelper();
    LinearLayoutManager linearLayoutManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        linearLayoutManager = new LinearLayoutManager(context, 0, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scaleItem();
            }
        });
        adapter.getItemManager().replaceAllItem(getItems());
    }

    public void scaleItem() {
        int first = linearLayoutManager.findFirstVisibleItemPosition();
        int last = linearLayoutManager.findLastVisibleItemPosition();
        int center = first + (last - first) / 2;
        int range = last - first;
        Log.i("position", first + ":" + last + ":" + center + ":" + range);
        for (int i = 0; i < range; i++) {
            RecyclerView.ViewHolder item = recyclerView.findViewHolderForLayoutPosition(first + i);
            if (item == null) {
                return;
            }
            if ((first + i) == center) {
                item.itemView.setScaleY(1.2f);
                continue;
            }
            item.itemView.setScaleY(1f);
        }
    }

    public List<TreeItem> getItems() {
        ArrayList<TreeItem> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(
                    new SimpleTreeItem(R.layout.item_grallery)
                            .setTreeOffset(new Rect(20, 20, 20, 20))
                            .onItemBind(viewHolder -> {
                                TextView itemView = (TextView) viewHolder.itemView;
                                itemView.setText(viewHolder.getLayoutPosition() + "");
                                viewHolder.itemView.setScaleY(1);
                            })
            );
        }
        return list;
    }
}
