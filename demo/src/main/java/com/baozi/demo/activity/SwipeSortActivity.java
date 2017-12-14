package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baozi.demo.R;
import com.baozi.demo.moudle.sortList.IndexBar;
import com.baozi.demo.moudle.sortList.SortGroupItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.adpater.wrapper.HeaderAndFootWrapper;
import com.baozi.treerecyclerview.adpater.wrapper.LoadingWrapper;
import com.baozi.treerecyclerview.adpater.wrapper.SwipeWrapper;
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.widget.TreeSortAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/8/19.
 * 在SortAcitivity的代码逻辑上,加少部分代码实现
 */

public class SwipeSortActivity extends AppCompatActivity {
    private static final String[] LETTERS = new String[]{"A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private TreeSortAdapter mTreeSortAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private LoadingWrapper mWrapper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        RecyclerView rlcontent = (RecyclerView) findViewById(R.id.rl_content);
        TextView tv_index = (TextView) findViewById(R.id.tv_index);
        IndexBar qb_sort = (IndexBar) findViewById(R.id.qb_sort);
        qb_sort.setOnIndexChangedListener(new IndexBar.OnIndexChangedListener() {
            @Override
            public void onIndexChanged(String letter) {
                int sortIndex = mTreeSortAdapter.getSortIndex(letter);
                mLinearLayoutManager.scrollToPositionWithOffset(sortIndex, 0);
            }
        });
        qb_sort.setIndexs(LETTERS);
        qb_sort.setSelectedIndexTextView(tv_index);

        mLinearLayoutManager = new GridLayoutManager(this,2);
        rlcontent.setLayoutManager(mLinearLayoutManager);
        rlcontent.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 2;
            }
        });
        //需求初定:好友列表
//        TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter();
//        treeRecyclerAdapter.setDatas(new ContentGroupItem());
        //第一次修改:添加索引
        //ContentGroupItem换成继承TreeSortItem
        //TreeSortAdapter继承TreeRecyclerAdapter.因为索引的item,一般是group类型.父容器
//        TreeSortAdapter sortAdapter = new TreeSortAdapter();
//        sortAdapter.setDatas(new ContentGroupItem());
//        //第二次修改:添加下拉刷新,上啦加载
//        //加载wapper
//        //需和网络请求绑定
//        LoadingWrapper<TreeItem> loadingWrapper = new LoadingWrapper<>(sortAdapter);
//        //第三次修改:添加侧滑菜单删除
//        //需要侧滑删除的item,实现SwipeItem接口.
//        SwipeWrapper swipeWrapper = new SwipeWrapper(loadingWrapper);
//        //还有其他的,也可以封装一个headItem,或者footItem,
//        HeaderAndFootWrapper<TreeItem> headerAndFootWrapper = new HeaderAndFootWrapper(swipeWrapper);
//        //....wrapper,item的种类会逐渐丰富.只是设计合理.还怕需求和加功能吗
        //创建索引adapter
        mTreeSortAdapter = new TreeSortAdapter();
//        mTreeSortAdapter.setType(TreeRecyclerType.SHOW_ALL);
        HeaderAndFootWrapper headerAndFootWrapper = new HeaderAndFootWrapper<>(mTreeSortAdapter);
        //添加头部View1
        TextView headView1 = new TextView(this);
        headView1.setText("headView");
        headView1.setGravity(Gravity.CENTER);
        headView1.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320));
        headerAndFootWrapper.addHeaderView(headView1);
        //包装成侧滑删除列表
        SwipeWrapper adapter = new SwipeWrapper(headerAndFootWrapper);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewHolder viewHolder, int position) {
                Toast.makeText(viewHolder.itemView.getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });
        mWrapper = new LoadingWrapper<>(adapter);
//        mWrapper.setType(LoadingWrapper.Type.LOADING);
        mWrapper.setEmptyView(R.layout.layout_empty);
        mWrapper.setLoadingView(R.layout.layout_loading);
        rlcontent.setAdapter(mWrapper);
        initData();
    }


    private void initData() {
        final List<TreeItem> groupItems = new ArrayList<>();
        for (int i = 0; i < LETTERS.length; i++) {
            SortGroupItem sortGroupItem = new SortGroupItem();
            sortGroupItem.setSortKey(LETTERS[i]);
            sortGroupItem.setData(5);
            groupItems.add(sortGroupItem);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWrapper.setType(LoadingWrapper.Type.SUCCESS);
                        mWrapper.getItemManager().replaceAllItem(new ArrayList());
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWrapper.setType(LoadingWrapper.Type.LOADING);
                    }
                });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWrapper.setType(LoadingWrapper.Type.SUCCESS);
                        mWrapper.getItemManager().replaceAllItem(groupItems);
                    }
                });
            }
        }).start();


    }
}
