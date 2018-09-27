package com.baozi.demo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.demo.R;
import com.baozi.demo.item.mine.MineItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.Arrays;
import java.util.List;

//RecyclerView实现首页我的页面
public class MineFragment extends Fragment {

    private RecyclerView view;
    private TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter();
    private List<String> name = Arrays.asList("条目1", "条目2", "条目3", "条目4", "条目5", "条目6");

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
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view.setAdapter(treeRecyclerAdapter);

        //添加条目
        List<TreeItem> items = ItemHelperFactory.createTreeItemList(name, MineItem.class, null);
        treeRecyclerAdapter.getItemManager().replaceAllItem(items);
    }
}
