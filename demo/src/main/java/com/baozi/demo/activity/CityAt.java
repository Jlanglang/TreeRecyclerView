package com.baozi.demo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.baozi.demo.R;
import com.baozi.demo.item.city.ProvinceBean;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 城市列表
 */
public class CityAt extends AppCompatActivity {
    //根据item的状态展示,可折叠
    TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_EXPAND);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_rv_content);
        RecyclerView recyclerView = findViewById(R.id.rv_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(treeRecyclerAdapter);
        new Thread() {
            @Override
            public void run() {
                super.run();
                String string = getFromAssets("city.txt");
                List<ProvinceBean> cityBeen = JSON.parseArray(string, ProvinceBean.class);
                refresh(cityBeen);
            }
        }.start();

    }

    public String getFromAssets(String fileName) {
        StringBuilder result = new StringBuilder();
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            while ((line = bufReader.readLine()) != null)
                result.append(line);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private void refresh(final List<ProvinceBean> cityBeen) {
        runOnUiThread(() -> {
            //创建item
            //新的
            List<TreeItem> items = ItemHelperFactory.createItems(cityBeen);
            //添加到adapter
            treeRecyclerAdapter.getItemManager().replaceAllItem(items);
        });

    }
}
