package com.baozi.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.baozi.demo.R;
import com.baozi.demo.bean.ProvinceBean;
import com.baozi.demo.item.city.ProvinceItemParent;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.util.List;

/**
 * 城市列表
 */
public class CityListActivity extends AppCompatActivity {
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
                String string = getString(R.string.location);
                List<ProvinceBean> cityBeen = JSON.parseArray(string, ProvinceBean.class);
                refresh(cityBeen);
            }
        }.start();

    }

    private void refresh(final List<ProvinceBean> cityBeen) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ItemHelperFactory.createTreeItemList(cityBeen, ProvinceItemParent.class, null);
                //创建item
                List<TreeItem> items = ItemHelperFactory.createItems(cityBeen, null);
                //遍历设置展开状态
                for (TreeItem item : items) {
                    if (item instanceof TreeItemGroup) {
                        ((TreeItemGroup) item).setExpand(true);
                    }
                }
                //添加到adapter
                treeRecyclerAdapter.getItemManager().replaceAllItem(items);
            }
        });
    }
}
