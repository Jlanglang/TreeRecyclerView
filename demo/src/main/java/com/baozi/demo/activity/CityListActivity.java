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
import com.baozi.demo.bean.CityBean;
import com.baozi.demo.item.citylist.ProvinceItemParent;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class CityListActivity extends AppCompatActivity {
    TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rv_content);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        String string = getResources().getString(R.string.location);
        List<CityBean> cityBeen = JSON.parseArray(string, CityBean.class);
        treeRecyclerAdapter.setDatas(ItemHelperFactory.createTreeItemList(cityBeen, ProvinceItemParent.class, null));
        recyclerView.setAdapter(treeRecyclerAdapter);
    }
}
