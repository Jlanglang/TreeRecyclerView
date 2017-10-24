package com.baozi.demo.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.demo.moudle.shoptablist.ContentItem;
import com.baozi.demo.moudle.shoptablist.TabItem;
import com.baozi.demo.moudle.shoptablist.bean.ShopTabBean;
import com.baozi.demo.moudle.shoptablist.bean.ShopTabContentBean;
//import com.baozi.treerecyclerview.adpater.ItemRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/5/10.
 */

public class ShopTabListActivity extends Activity {
    private RecyclerView mTabRecyclerView;
    private RecyclerView mContentRecyclerView;
    private TreeRecyclerAdapter mContentAdapter;
    private TreeRecyclerAdapter mTabAdapter;
    private List<TreeItem> mTabItemList;
    private List<TreeItem> mContentItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_tab_list);
        initTabRecycler();
        initContentRecycler();
    }

    /**
     * 初始化tab列表的RecyclerView
     */
    private void initTabRecycler() {
        ArrayList<ShopTabBean> mTabBeanArrayList = new ArrayList<>();
        ShopTabBean tabBean0 = new ShopTabBean();
        tabBean0.setName("全部");
        ShopTabBean tabBean1 = new ShopTabBean();
        tabBean1.setName("1");
        ShopTabBean tabBean2 = new ShopTabBean();
        tabBean2.setName("2");
        ShopTabBean tabBean3 = new ShopTabBean();
        tabBean3.setName("3");
        ShopTabBean tabBean4 = new ShopTabBean();
        tabBean4.setName("4");
        ShopTabBean tabBean5 = new ShopTabBean();
        tabBean5.setName("5");
        mTabBeanArrayList.add(tabBean0);
        mTabBeanArrayList.add(tabBean1);
        mTabBeanArrayList.add(tabBean2);
        mTabBeanArrayList.add(tabBean3);
        mTabBeanArrayList.add(tabBean4);
        mTabBeanArrayList.add(tabBean5);
        mTabItemList = ItemHelperFactory.createTreeItemList(mTabBeanArrayList, TabItem.class, null);

        mTabAdapter = new TreeRecyclerAdapter();
        mTabAdapter.setDatas(new ArrayList<TreeItem>(mTabItemList));
        mTabAdapter.setType(TreeRecyclerType.SHOW_ALL);

        mTabRecyclerView = (RecyclerView) findViewById(R.id.rl_tab);
        mTabRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 2;
            }
        });
        mTabAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewHolder viewHolder, int position) {
                ShopTabBean data = (ShopTabBean) mTabAdapter.getData(position).getData();
                String name = data.getName();
                int size = mContentItems.size();
                ArrayList<TreeItem> contentBeenList = new ArrayList<>();
                if (name.equals("全部")) {
                    mContentAdapter.getItemManager().replaceAllItem(mContentItems);
                } else {
                    for (int i = 0; i < size; i++) {
                        ShopTabContentBean shopTabContentBean = (ShopTabContentBean) mContentItems.get(i).getData();
                        if (shopTabContentBean.getName().equals(name)) {
                            contentBeenList.add(mContentItems.get(i));
                        }
                    }
                    mContentAdapter.getItemManager().replaceAllItem(contentBeenList);
                }
            }
        });
        mTabRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mTabRecyclerView.setAdapter(mTabAdapter);
    }

    /**
     * 初始化tab列表的RecyclerView
     */
    private void initContentRecycler() {
        ArrayList<ShopTabContentBean> mContentBeanArrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ShopTabContentBean shopTabContentBean = new ShopTabContentBean();
            shopTabContentBean.setTitle(i + "");
            shopTabContentBean.setName(i % 5 + 1 + "");
            mContentBeanArrayList.add(shopTabContentBean);
        }
        mContentItems = ItemHelperFactory.createTreeItemList(mContentBeanArrayList, ContentItem.class, null);

        mContentAdapter = new TreeRecyclerAdapter();
        mContentAdapter.setDatas(new ArrayList<TreeItem>(mContentItems));
        mContentRecyclerView = (RecyclerView) findViewById(R.id.rl_content);
        mContentRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 2;
            }
        });
        mContentRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mContentRecyclerView.setAdapter(mContentAdapter);


    }

}
