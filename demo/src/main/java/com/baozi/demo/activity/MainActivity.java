package com.baozi.demo.activity;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.demo.item.mine.MineItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.SimpleTreeItem;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jlanglang  2016/12/22 9:58
 * @版本 2.0
 * @Change
 */
public class MainActivity extends AppCompatActivity {
    //数据集合
    private Pair[] itemPairs = {
            new Pair("城市列表", CityListActivity.class),
            new Pair("购物车列表", CartActivity.class),
            new Pair("新闻混合列表", NewsActivity.class),
            new Pair("索引列表", SortActivity.class),
            new Pair("索引加侧滑删除列表", SwipeSortActivity.class)
    };
    private TreeRecyclerAdapter adapter = new TreeRecyclerAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rv_content);
        initRecyclerVIew();
        initData();
    }

    /**
     * 简单创建Item
     */
    private void initData() {
        ArrayList<TreeItem> items = new ArrayList<>();
        for (Pair itemPair : itemPairs) {
            SimpleTreeItem simpleTreeItem = new SimpleTreeItem(R.layout.item_mine)
                    .setTreeBind(new SimpleTreeItem.Consumer<ViewHolder>() {
                        @Override
                        public void accept(ViewHolder viewHolder) {
                            Pair itemPair = itemPairs[viewHolder.getLayoutPosition()];
                            viewHolder.setText(R.id.tv_name, (String) itemPair.first);
                        }
                    })
                    .setTreeClick(new SimpleTreeItem.Consumer<ViewHolder>() {
                        @Override
                        public void accept(ViewHolder viewHolder) {
                            Pair itemPair = itemPairs[viewHolder.getLayoutPosition()];
                            startAt((Class) itemPair.second);
                        }
                    });
            items.add(simpleTreeItem);
        }
        adapter.getItemManager().replaceAllItem(items);
    }

    /**
     * 初始化列表
     */
    private void initRecyclerVIew() {
        RecyclerView rv_content = findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
        rv_content.setAdapter(adapter);
        rv_content.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 10;
            }
        });
    }

    public void startAt(Class zClass) {
        Intent intent = new Intent(this, zClass);
        startActivity(intent);
    }

    public void startFragment(Fragment fragment) {

    }
}
