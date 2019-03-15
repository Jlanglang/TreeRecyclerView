package com.baozi.demo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.demo.R;
import com.baozi.demo.item.clickload.ClickLoadGroupItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 点击懒加载的demo
 */
public class ClickLoadFragment extends Fragment {
    RecyclerView view;
    private TreeRecyclerAdapter adapter = new TreeRecyclerAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = (RecyclerView) inflater.inflate(R.layout.layout_rv_content, container, false);
            view.setBackgroundColor(Color.GRAY);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化recyclerView
        view.setLayoutManager(new GridLayoutManager(view.getContext(), 4));
        view.setAdapter(adapter);
        TreeItem treeItem = ItemHelperFactory.createTreeItem(new String[]{}, ClickLoadGroupItem.class, null);
        adapter.getItemManager().replaceAllItem(Arrays.asList(treeItem));
        adapter.setOnItemClickListener((viewHolder, position) -> {
            ClickLoadGroupItem data = (ClickLoadGroupItem) adapter.getData(position);
            if (data == null || data.getData() == null) {
                return;
            }
            if (data.getChild() == null || data.getChild().isEmpty()) {
                data.setData(new String[]{"1", "2", "3"});
                data.setExpand(true);
            }
        });
    }
}
