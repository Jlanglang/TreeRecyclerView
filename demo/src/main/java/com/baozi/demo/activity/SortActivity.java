package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baozi.demo.R;
import com.baozi.demo.item.sort.IndexBar;
import com.baozi.demo.item.sort.SortGroupItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.widget.TreeSortAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/8/19.
 */

public class SortActivity extends AppCompatActivity {
    private static final String[] LETTERS = new String[]{"A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private TreeSortAdapter mTreeSortAdapter;
    private LinearLayoutManager mLinearLayoutManager;

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

        mLinearLayoutManager = new LinearLayoutManager(this);
        rv_content.setLayoutManager(mLinearLayoutManager);
        rv_content.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 2;
            }
        });
        mTreeSortAdapter = new TreeSortAdapter();
        mTreeSortAdapter.setType(TreeRecyclerType.SHOW_ALL);

        rv_content.setAdapter(mTreeSortAdapter);
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
        mTreeSortAdapter.getItemManager().replaceAllItem(groupItems);
    }
}
