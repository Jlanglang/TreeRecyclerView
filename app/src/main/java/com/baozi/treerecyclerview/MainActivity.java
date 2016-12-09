package com.baozi.treerecyclerview;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewAdapter;
import com.baozi.treerecyclerview.bean.CityBean;
import com.baozi.treerecyclerview.treeitem.OneItem;
import com.baozi.treerecyclerview.viewholder.TreeAdapterItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rl_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this,6));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 10;
                if (view.getLayoutParams() instanceof GridLayoutManager.LayoutParams) {
                    GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                    int spanIndex = layoutParams.getSpanIndex();//在一行中所在的角标，第几列
//                    int spanSize = layoutParams.getSpanSize();//所占的比例
                    if (spanIndex == ((GridLayoutManager) parent.getLayoutManager()).getSpanCount() - 1) {

                    } else {
                        outRect.right = 10;
                    }
                }
            }
        });
        ArrayList<TreeAdapterItem> treeBeen1 = new ArrayList<>();//一级
        List<CityBean> cityBeen = JSON.parseArray(getResources().getString(R.string.location), CityBean.class);
        for (int i = 0; i < cityBeen.size(); i++) {
            treeBeen1.add(new OneItem(cityBeen.get(i)));
        }
        recyclerView.setAdapter(new TreeRecyclerViewAdapter<>(this, treeBeen1));
    }

}
