package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.baozi.demo.R;
import com.baozi.demo.moudle.shoplist.ShopGroupItem;
import com.baozi.demo.moudle.shoplist.bean.ShopListBean;
import com.baozi.demo.moudle.shoplist.bean.StoreBean;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.adpater.wrapper.HeaderAndFootWrapper;
//import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;
import java.util.List;

//import com.baozi.treerecyclerview.view.HeadAndFootGroupWapper;

/**
 * Created by baozi on 2016/12/22.
 */

public class ShopListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TreeRecyclerAdapter mAdapter;
    private TextView mTvNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        mRecyclerView = (RecyclerView) findViewById(R.id.rl_content);
        mTvNext = (TextView) findViewById(R.id.tv_next);
        mTvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNext();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 2;
            }
        });
        List<StoreBean> storeBean = initData();
        List<TreeItem> itemList = ItemHelperFactory.createTreeItemList(storeBean, ShopGroupItem.class, null);

        mAdapter = new TreeRecyclerAdapter();
        mAdapter.setType(TreeRecyclerType.SHOW_ALL);
        mAdapter.setDatas(itemList);
        //包装成可以添加头部和尾部的Adapter
        HeaderAndFootWrapper<TreeItem> headerAndFootWapper = new HeaderAndFootWrapper<TreeItem>(mAdapter) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        };
        //添加头部View1
        TextView headView1 = new TextView(this);
        headView1.setText("headView");
        headView1.setGravity(Gravity.CENTER);
        headView1.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320));
        headerAndFootWapper.addHeaderView(headView1);
        //添加底部View1
        TextView footView1 = new TextView(this);
        footView1.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320));
        footView1.setText("footView");
        headerAndFootWapper.addFootView(footView1);
        mRecyclerView.setAdapter(headerAndFootWapper);
    }

    public void onNext() {
        List<StoreBean> shopListBeen = new ArrayList<>();
        List<TreeItem> datas = mAdapter.getDatas();
        for (int i = 0; i < datas.size(); i++) {
            TreeItem treeItem = datas.get(i);
            if (treeItem instanceof ShopGroupItem) {
                ShopGroupItem titletItem = (ShopGroupItem) datas.get(i);
                StoreBean data = titletItem.getData();
                if (titletItem.isChildCheck()) {
                    ArrayList<ShopListBean> shopListBeens = new ArrayList<>();
                    List<? extends TreeItem> childs = titletItem.getSelectItems();
                    for (int j = 0; j < childs.size(); j++) {
                        shopListBeens.add((ShopListBean) childs.get(j).getData());
                    }
                    //这里不行.应该是new一个
                    data.setShopListBeen(shopListBeens);
                    shopListBeen.add(data);
                }
            }
        }
        String s = JSON.toJSONString(shopListBeen);
        Log.i("data", s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private List<StoreBean> initData() {
        ArrayList<StoreBean> storeBeens = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StoreBean storeBean = new StoreBean();
            storeBean.setStoreId(i);
            storeBean.setStoreName("商店" + i);
            storeBean.setTitle("商店" + i);

            ArrayList<ShopListBean> shopListBeens = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                ShopListBean shopListBean = new ShopListBean();
                shopListBean.setShopName("豆腐渣");
                shopListBean.setShopId(i);
                shopListBeens.add(shopListBean);
            }
            storeBean.setShopListBeen(shopListBeens);
            storeBeens.add(storeBean);
        }
        return storeBeens;
    }

}
