package com.baozi.demo.fragment;

import android.graphics.Color;
import android.graphics.Rect;
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
import com.baozi.demo.item.mine.MineCategoryBean;
import com.baozi.demo.item.mine.MineItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.SimpleTreeItem;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.Arrays;
import java.util.List;

//RecyclerView实现首页我的页面
public class MineFg extends Fragment {

    private RecyclerView view;
    private TreeRecyclerAdapter adapter = new TreeRecyclerAdapter();
    private List<MineCategoryBean> heads = Arrays.asList(
            new MineCategoryBean("公开文章", null, "39", true),
            new MineCategoryBean("关注", null, "57", true),
            new MineCategoryBean("粉丝", null, "501", true),
            new MineCategoryBean("每日签到", "1", null, true)
    );
    private List<MineCategoryBean> category = Arrays.asList(
            new MineCategoryBean("私密文章", "1", null),
            new MineCategoryBean("我的收藏", "1", null),
            new MineCategoryBean("我的喜欢", "1", null),
            new MineCategoryBean("已购内容", "1", null),
            new MineCategoryBean("我的专题", "1", null, true),
            new MineCategoryBean("我的文集", "1", null, true),
            new MineCategoryBean("关注内容", "1", null, true),
            new MineCategoryBean("我的钱包", "1", null, true)
    );
    private List<String> item = Arrays.asList("条目1", "条目2", "条目3", "条目4", "条目5", "条目6");

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
        adapter.getItemManager().clean();
        //添加头部
        adapter.getItemManager().addItem(
                new SimpleTreeItem(R.layout.item_mine_head)
                        .setTreeOffset(new Rect(0, 0, 0, 2))
        );
        //添加头部分类item
        adapter.getItemManager().addItems(ItemHelperFactory.createItems(heads));
        //添加分类item
        adapter.getItemManager().addItems(ItemHelperFactory.createItems(category));
        //添加横条item
        adapter.getItemManager().addItems(ItemHelperFactory.createItems(item, MineItem.class));
    }
}
