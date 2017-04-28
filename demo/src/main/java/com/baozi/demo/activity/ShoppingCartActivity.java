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
import com.baozi.demo.viewholder.shopcart.ContentItem;
import com.baozi.demo.viewholder.shopcart.ShopListBean;
import com.baozi.demo.viewholder.shopcart.StoreBean;
import com.baozi.demo.viewholder.shopcart.ShopTitileItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;
import com.baozi.treerecyclerview.helper.ItemHelper;
import com.baozi.treerecyclerview.view.BaseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TreeRecyclerAdapter<ShopTitileItem> mTitleItemTreeRecyclerViewAdapter;
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
        List<ShopTitileItem> itemList = ItemHelper.createItemList(storeBean, ShopTitileItem.class);
        mTitleItemTreeRecyclerViewAdapter = new TreeRecyclerAdapter<>();
        mTitleItemTreeRecyclerViewAdapter.setType(TreeRecyclerViewType.SHOW_ALL);
        mTitleItemTreeRecyclerViewAdapter.setDatas(itemList);
        mRecyclerView.setAdapter(mTitleItemTreeRecyclerViewAdapter);
    }

    public void onNext() {
        List<StoreBean> shopListBeen = new ArrayList<>();
        List<ShopTitileItem> datas = mTitleItemTreeRecyclerViewAdapter.getInitialDatas();
        for (int i = 0; i < datas.size(); i++) {
            ShopTitileItem titletItem = datas.get(i);
            StoreBean data = titletItem.getData();
            if (data.isCheck()) {
                ArrayList<ShopListBean> shopListBeens = new ArrayList<>();
                List<? extends BaseItem> childs = titletItem.getChilds();
                for (int j = 0; j < childs.size(); j++) {
                    ContentItem baseItem = (ContentItem) childs.get(j);
                    if (baseItem.getData().isCheck()) {
                        shopListBeens.add(baseItem.getData());
                    }
                }
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
