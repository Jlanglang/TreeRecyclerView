package com.baozi.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.demo.item.cart.CartGroupItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by a123 on 2018/6/5.
 */

public class CartActivity extends Activity {
    private TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rv_content);
        RecyclerView rv_content = findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
        rv_content.setAdapter(treeRecyclerAdapter);

        List<String> integers = Arrays.asList("1", "1", "1", "1", "1");
        List<TreeItem> treeItemList = ItemHelperFactory.createTreeItemList(integers, CartGroupItem.class, null);

        treeRecyclerAdapter.getItemManager().replaceAllItem(treeItemList);
    }
}
