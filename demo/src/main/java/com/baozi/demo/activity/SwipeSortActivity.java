package com.baozi.demo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baozi.demo.R;
import com.baozi.demo.item.sort.IndexBar;
import com.baozi.demo.item.sort.SortGroupItem;
import com.baozi.treerecyclerview.adpater.wrapper.HeaderAndFootWrapper;
import com.baozi.treerecyclerview.adpater.wrapper.SwipeWrapper;
import com.baozi.treerecyclerview.adpater.wrapper.TreeLoadWrapper;
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.SimpleTreeItem;
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
    private TreeLoadWrapper mWrapper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        RecyclerView rv_content = (RecyclerView) findViewById(R.id.rv_content);
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

        mLinearLayoutManager = new GridLayoutManager(this, 2);
        rv_content.setLayoutManager(mLinearLayoutManager);
        //创建索引adapter
        mTreeSortAdapter = new TreeSortAdapter();
        mTreeSortAdapter.getItemManager().setOpenAnim(true);
        HeaderAndFootWrapper headerAndFootWrapper = new HeaderAndFootWrapper<>(mTreeSortAdapter);
        addHeadView(headerAndFootWrapper, 5);
        //包装成侧滑删除列表
        SwipeWrapper adapter = new SwipeWrapper(headerAndFootWrapper);
//        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(@NonNull ViewHolder viewHolder, int position) {
//                Toast.makeText(viewHolder.itemView.getContext(), position + "", Toast.LENGTH_SHORT).show();
//            }
//        });
        mWrapper = new TreeLoadWrapper(adapter);
        mWrapper.setEmptyView(new SimpleTreeItem(R.layout.layout_empty));
        mWrapper.setLoadingView(R.layout.layout_loading);

        rv_content.setAdapter(mWrapper);

        initData();
    }

    private void addHeadView(HeaderAndFootWrapper headerAndFootWrapper, int sum) {
        for (int i = 0; i < sum; i++) {
            //添加头部View1
            TextView headView = new TextView(this);
            headView.setText("headView" + i);
            headView.setGravity(Gravity.CENTER);
            headView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320));
            headerAndFootWrapper.addHeaderView(headView);
        }
    }

    private void initData() {
        final List<TreeItem> groupItems = new ArrayList<>();
        for (int i = 0; i < LETTERS.length; i++) {
            SortGroupItem sortGroupItem = new SortGroupItem();
            sortGroupItem.setSortKey(LETTERS[i]);
            sortGroupItem.setData(i);
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
                        mWrapper.setType(TreeLoadWrapper.Type.REFRESH_OVER);
                        mWrapper.getItemManager().notifyDataChanged();
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
                        mWrapper.setType(TreeLoadWrapper.Type.LOADING);
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
                        mWrapper.setType(TreeLoadWrapper.Type.REFRESH_OVER);
                        mWrapper.getItemManager().replaceAllItem(groupItems);
                    }
                });
            }
        }).start();


    }
}
