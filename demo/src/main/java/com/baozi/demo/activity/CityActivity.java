package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.baozi.demo.R;
import com.baozi.demo.viewholder.citytree.CityBean;
import com.baozi.demo.viewholder.citytree.OneTreeParentItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        recyclerView = (RecyclerView) findViewById(R.id.rl_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
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
        ArrayList<OneTreeParentItem> treeBeen1 = new ArrayList<>();//一级
        List<CityBean> cityBeen = JSON.parseArray(getResources().getString(R.string.location), CityBean.class);
        for (int i = 0; i < cityBeen.size(); i++) {
            treeBeen1.add(new OneTreeParentItem(cityBeen.get(i)));
        }
        recyclerView.setAdapter(new TreeRecyclerViewAdapter<>(this, treeBeen1));
    }

}
