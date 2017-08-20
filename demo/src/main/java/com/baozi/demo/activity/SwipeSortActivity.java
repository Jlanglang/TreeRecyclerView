package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baozi.demo.R;
import com.baozi.demo.moudle.sortList.IndexBar;
import com.baozi.demo.moudle.sortList.SortGroupItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;
import com.baozi.treerecyclerview.adpater.wrapper.HeaderAndFootWrapper;
import com.baozi.treerecyclerview.adpater.wrapper.LoadingWrapper;
import com.baozi.treerecyclerview.adpater.wrapper.SwipeWrapper;
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
    private SwipeWrapper mSwipeWrapper;

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

        mLinearLayoutManager = new LinearLayoutManager(this);
        rlcontent.setLayoutManager(mLinearLayoutManager);
        rlcontent.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 2;
            }
        });
        //创建索引adapter
        mTreeSortAdapter = new TreeSortAdapter();
        mTreeSortAdapter.setType(TreeRecyclerViewType.SHOW_ALL);
        //一行包装成侧滑删除列表
        mSwipeWrapper = new SwipeWrapper(mTreeSortAdapter);
        //包装头尾
        HeaderAndFootWrapper headerAndFootWrapper = new HeaderAndFootWrapper<>(mSwipeWrapper);
        //包装加载
        LoadingWrapper loadingWrapper = new LoadingWrapper<>(headerAndFootWrapper);

        rlcontent.setAdapter(mSwipeWrapper);
        initData();
    }


    private void initData() {
        List<TreeItem> groupItems = new ArrayList<>();
        for (int i = 0; i < LETTERS.length; i++) {
            SortGroupItem sortGroupItem = new SortGroupItem();
            sortGroupItem.setSortKey(LETTERS[i]);
            sortGroupItem.setData(5);
            groupItems.add(sortGroupItem);
        }
        mSwipeWrapper.getItemManager().replaceAllItem(groupItems);
    }
}
