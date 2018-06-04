package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.demo.item.testlist.ContentImageItem;
import com.baozi.demo.bean.ContentBean;
import com.baozi.demo.item.testlist.SelectionImageItem;
import com.baozi.demo.item.testlist.SelectionTextItem;
import com.baozi.demo.bean.TitleBean;
import com.baozi.demo.item.testlist.ContentGroupItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.factory.ItemConfig;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/4/27.
 */

public class TestListAativity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rv_content);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<TitleBean> titleBeens = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            TitleBean titleBean = new TitleBean();
            titleBeens.add(titleBean);
        }
        List<TreeItem> itemList = ItemHelperFactory.createItems(titleBeens, null);
        TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL);
        treeRecyclerAdapter.setDatas(itemList);
        recyclerView.setAdapter(treeRecyclerAdapter);
    }
}
