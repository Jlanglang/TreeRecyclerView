package com.baozi.demo.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.demo.fragment.ClickLoadFg;
import com.baozi.demo.fragment.MineFg;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.item.SimpleTreeItem;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;

/**
 * @author jlanglang  2016/12/22 9:58
 * @版本 2.0
 * @Change
 */
public class MainActivity extends AppCompatActivity {
    //数据集合
    private Pair[] itemPairs = {
            new Pair("三级城市", CityAt.class),
            new Pair("购物车", CartAt.class),
            new Pair("新闻", NewsAt.class),
            new Pair("索引", SortAt.class),
            new Pair("索引加侧滑删除", SwipeSortAt.class),
            new Pair("个人中心", MineFg.class),
            new Pair("淘宝首页", TBAtivity.class),
            new Pair("点击懒加载", ClickLoadFg.class)
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
                    .onItemBind(viewHolder -> {
                        Pair itemPair1 = itemPairs[viewHolder.getLayoutPosition()];
                        viewHolder.setText(R.id.tv_name, (String) itemPair1.first);
                    })
                    .onItemClick(viewHolder -> {
                        Pair itemPair12 = itemPairs[viewHolder.getLayoutPosition()];
                        Class<?> aClass = (Class<?>) itemPair12.second;
                        boolean assignableFrom = Fragment.class.isAssignableFrom(aClass);//判断是不是fragment的子类
                        if (assignableFrom) {
                            startFragment((Class<Fragment>) itemPair12.second);
                        } else {
                            startAt((Class) itemPair12.second);
                        }
                    });
            simpleTreeItem.setData(itemPair);
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

    public void startFragment(Class<Fragment> zClass) {
        try {
            Fragment fragment = zClass.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(android.R.id.content, fragment, null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
