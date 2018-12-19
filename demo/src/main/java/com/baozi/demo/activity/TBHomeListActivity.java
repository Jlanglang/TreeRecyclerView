package com.baozi.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;

public class TBHomeListActivity extends AppCompatActivity {
    private TreeRecyclerAdapter adapter = new TreeRecyclerAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb_home);
        RecyclerView rv_content = findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new GridLayoutManager(this, 6));
    }
}
