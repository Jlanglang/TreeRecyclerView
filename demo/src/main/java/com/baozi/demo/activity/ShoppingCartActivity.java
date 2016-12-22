package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.demo.viewholder.shopcart.ContentItem;
import com.baozi.demo.viewholder.shopcart.ShopListBean;
import com.baozi.demo.viewholder.shopcart.StoreBean;
import com.baozi.demo.viewholder.shopcart.TitleItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerViewType;
import com.baozi.treerecyclerview.viewholder.TreeAdapterItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.rl_content);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 2;
            }
        });
        ArrayList<TitleItem> titleItems = new ArrayList<>();//一级
        List<StoreBean> storeBean = initData();
        for (int i = 0; i < storeBean.size(); i++) {
            titleItems.add(new TitleItem(storeBean.get(i)));
        }
        final TreeRecyclerViewAdapter<TitleItem> titleItemTreeRecyclerViewAdapter = new TreeRecyclerViewAdapter<>(this, titleItems, TreeRecyclerViewType.SHOW_ALL);
        mRecyclerView.setAdapter(titleItemTreeRecyclerViewAdapter);
//        titleItemTreeRecyclerViewAdapter.setOnTreeItemClickListener(new TreeRecyclerViewAdapter.OnTreeItemClickListener() {
//            @Override
//            public void onClick(TreeAdapterItem node, ViewHolder holder, int position) {
//                if (node instanceof TitleItem) {
//                    StoreBean data = ((TitleItem) node).getData();
//                    data.setCheck(!data.isCheck());
//                    List childs = node.getChilds();
//                    if (childs != null) {
//                        for (int i = 0; i < childs.size(); i++) {
//                            ContentItem contentItem = (ContentItem) childs.get(i);
//                            ShopListBean data1 = contentItem.getData();
//                            data1.setCheck(data.isCheck());
//                        }
//                    }
//                } else if (node instanceof ContentItem) {
//                    ContentItem contentItem = (ContentItem) node;
//                    ShopListBean data1 = contentItem.getData();
//                    data1.setCheck(!data1.isCheck());
//                }
//                titleItemTreeRecyclerViewAdapter.notifyDataSetChanged();
//            }
//        });
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
