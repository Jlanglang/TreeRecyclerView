package com.baozi.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.baozi.demo.R;
import com.baozi.demo.bean.ProvinceBean;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;

import java.util.List;

public class CityListActivity extends AppCompatActivity {
    TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rv_content);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));

        String string = getResources().getString(R.string.location);
        List<ProvinceBean> cityBeen = JSON.parseArray(string, ProvinceBean.class);
        treeRecyclerAdapter.getItemManager().replaceAllItem(ItemHelperFactory.createItems(cityBeen, null));
        recyclerView.setAdapter(treeRecyclerAdapter);
    }
}
