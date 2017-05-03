package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.baozi.demo.R;
import com.baozi.demo.demo.shop.ContentItem;
import com.baozi.demo.demo.shop.HeadViewItem;
import com.baozi.demo.demo.shop.bean.ShopListBean;
import com.baozi.demo.demo.shop.bean.StoreBean;
import com.baozi.demo.demo.shop.ShopTitileItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;
import com.baozi.treerecyclerview.adpater.wapper.HeaderAndFootWapper;
import com.baozi.treerecyclerview.factory.ItemFactory;
import com.baozi.treerecyclerview.base.BaseItem;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TreeRecyclerAdapter<ShopTitileItem> mAdapter;
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
        List<ShopTitileItem> itemList = ItemFactory.createItemList(storeBean, ShopTitileItem.class);
        mAdapter = new TreeRecyclerAdapter<>();
        mAdapter.setType(TreeRecyclerViewType.SHOW_ALL);
        mAdapter.setDatas(itemList);
        //包装成可以添加头部和尾部的Adapter
        HeaderAndFootWapper<ShopTitileItem> headerAndFootWapper = new HeaderAndFootWapper<>(mAdapter);
        //添加头部View1
        TextView headView1 = new TextView(this);
        headView1.setText("headview");
        headView1.setGravity(Gravity.CENTER);
        headView1.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320));
        headerAndFootWapper.addHeaderView(headView1);
        //添加头部View2
        ImageView headView2 = new ImageView(this);
        headView2.setBackgroundResource(R.mipmap.ic_launcher);
        headView2.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320));
        headerAndFootWapper.addHeaderView(headView2);
        //添加底部View1
        ImageView footView1 = new ImageView(this);
        footView1.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320));
        footView1.setBackgroundResource(R.mipmap.ic_launcher);
        headerAndFootWapper.addFootView(footView1);

        mRecyclerView.setAdapter(headerAndFootWapper);
    }

    public void onNext() {
        List<StoreBean> shopListBeen = new ArrayList<>();
        List<ShopTitileItem> datas = mAdapter.getInitialDatas();
        for (int i = 0; i < datas.size(); i++) {
            ShopTitileItem titletItem = datas.get(i);
            StoreBean data = titletItem.getData();
            if (titletItem.isHaveCheck()) {
                ArrayList<ShopListBean> shopListBeens = new ArrayList<>();
                List<? extends BaseItem> childs = titletItem.getSelectItems();
                for (int j = 0; j < childs.size(); j++) {
                    shopListBeens.add((ShopListBean) childs.get(j).getData());
                }
                //这里不行.应该是new一个
                data.setShopListBeen(shopListBeens);
                shopListBeen.add(data);
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
