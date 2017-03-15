package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.baozi.demo.R;
import com.baozi.demo.viewholder.shopcart.BrandBean;
import com.baozi.demo.viewholder.shopcart.BrandItem;
import com.baozi.demo.viewholder.shopcart.ShopListBean;
import com.baozi.demo.viewholder.shopcart.StoreBean;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TreeRecyclerViewAdapter<BrandItem> mTitleItemTreeRecyclerViewAdapter;
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
        ArrayList<BrandItem> brandItems = new ArrayList<>();//一级
        List<BrandBean> brandBeen = initData();
        for (int i = 0; i < brandBeen.size(); i++) {
            brandItems.add(new BrandItem(brandBeen.get(i)));
        }
        mTitleItemTreeRecyclerViewAdapter = new TreeRecyclerViewAdapter<>(this, brandItems, TreeRecyclerViewType.SHOW_ALL);
        mRecyclerView.setAdapter(mTitleItemTreeRecyclerViewAdapter);
    }

    public void onNext() {
        List<BrandItem> fristDatas = mTitleItemTreeRecyclerViewAdapter.getFristDatas();
        ArrayList<BrandBean> brandBeenList = new ArrayList<>();
        for (int i = 0; i < fristDatas.size(); i++) {
            BrandItem brandItem = fristDatas.get(i);
            BrandBean data = brandItem.getData();
            ArrayList<StoreBean> storeBeen = new ArrayList<>(data.getStoreBeens());
            Iterator<StoreBean> iterator = storeBeen.iterator();
            while (iterator.hasNext()) {
                StoreBean next = iterator.next();
                if (!next.isCheck()) {
                    iterator.remove();
                } else {
                    ArrayList<ShopListBean> shopListBeen = new ArrayList<>(next.getShopListBeen());
                    Iterator<ShopListBean> shopListBeanIterator = shopListBeen.iterator();
                    while (shopListBeanIterator.hasNext()) {
                        ShopListBean shopListBean = shopListBeanIterator.next();
                        if (!shopListBean.isCheck()) {
                            shopListBeanIterator.remove();
                        }
                    }
                    next.setShopListBeen(shopListBeen);
                }
            }
            BrandBean brandBean = new BrandBean();
            brandBean.setBrandId(data.getBrandId());
            brandBean.setTitle(data.getTitle());
            brandBean.setBrandName(data.getBrandName());
            brandBean.setStoreBeens(storeBeen);
            if (storeBeen.size() > 0) {
                brandBeenList.add(brandBean);
            }
        }
        String s = JSON.toJSONString(brandBeenList);
        Log.i("data", s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private List<BrandBean> initData() {
        ArrayList<BrandBean> brandBeens = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ArrayList<StoreBean> storeBeens = new ArrayList<>();
            for (int x = 0; x < 3; x++) {
                ArrayList<ShopListBean> shopListBeens = new ArrayList<>();
                for (int y = 0; y < 3; y++) {
                    ShopListBean shopListBean = new ShopListBean();
                    shopListBean.setShopName("豆腐渣");
                    shopListBean.setShopId(i);
                    shopListBeens.add(shopListBean);
                }
                StoreBean storeBean = new StoreBean();
                storeBean.setStoreId(i);
                storeBean.setStoreName("商店" + i);
                storeBean.setTitle("商店" + i);
                storeBean.setShopListBeen(shopListBeens);
                storeBeens.add(storeBean);
            }
            BrandBean brandBean = new BrandBean();
            brandBean.setTitle("渣渣牌");
            brandBean.setBrandId(0);
            brandBean.setBrandName("渣渣牌");
            brandBean.setStoreBeens(storeBeens);
            brandBeens.add(brandBean);
        }
        return brandBeens;
    }

}
