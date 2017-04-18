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
import com.baozi.demo.viewholder.citytree.OneItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewAdapter;
import com.baozi.treerecyclerview.viewholder.ItemHelper;

import java.util.List;

public class CityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rl_content);
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
                    if (spanIndex != ((GridLayoutManager) parent.getLayoutManager()).getSpanCount() - 1) {
                        outRect.right = 10;
                    }
                }
            }
        });
        List<CityBean> cityBeen = JSON.parseArray(getResources().getString(R.string.location), CityBean.class);
        List<OneItem> itemList = ItemHelper.createItemListForClass(cityBeen, OneItem.class);
        recyclerView.setAdapter(new TreeRecyclerViewAdapter<>(this, itemList));
    }

}
