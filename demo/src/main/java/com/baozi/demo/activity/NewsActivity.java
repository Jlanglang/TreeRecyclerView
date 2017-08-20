package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.demo.moudle.newslist.NewsFootItem;
import com.baozi.demo.moudle.newslist.NewsImageItem;
import com.baozi.demo.moudle.newslist.NewsItem;
import com.baozi.demo.moudle.newslist.bean.NewsItemBean;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.factory.ItemConfig;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jlanglang  2017/7/5 10:22
 * @版本 2017-8-19;有点勉强,不建议这样使用- -
 */

public class NewsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testlist);
        ItemConfig.addTreeHolderType(200, NewsItem.class);
        ItemConfig.addTreeHolderType(201, NewsImageItem.class);
        ItemConfig.addTreeHolderType(202, NewsFootItem.class);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rl_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (view.getLayoutParams() instanceof GridLayoutManager.LayoutParams) {
                    GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                    int spanIndex = layoutParams.getSpanIndex();//在一行中所在的角标，第几列
                    int spanSize = layoutParams.getSpanSize();
                    if (spanSize == 2) {
                        outRect.bottom = 10;
                    }
                    if (spanIndex == 0 && spanSize == 2) {
                        outRect.left = 30;
                        outRect.right = 10;
                    } else if (spanIndex == 4) {
                        outRect.left = 0;
                        outRect.right = 30;
                    } else if (spanSize == 6) {
                        outRect.left = 30;
                        outRect.right = 30;
                    } else {
                        outRect.left = 0;
                        outRect.right = 10;
                    }
                }
            }
        });
        ArrayList<NewsItemBean> newsItemBeens = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewsItemBean newsItemBean = new NewsItemBean();
            newsItemBean.setViewItemType(200);
            int size = 3;
            if (i % 2 == 0) {
                size = 5;
            }
            ArrayList<NewsItemBean.NewsImageBean> imageBeens = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                NewsItemBean.NewsImageBean imageBean = new NewsItemBean.NewsImageBean();
                imageBean.setViewItemType(201);
                imageBeens.add(imageBean);
            }
            newsItemBean.setImageBeanList(imageBeens);

            NewsItemBean.NewsFootBean newsFootBean = new NewsItemBean.NewsFootBean();
            newsFootBean.setViewItemType(202);
            newsItemBean.setFootBean(newsFootBean);
            newsItemBeens.add(newsItemBean);
        }

        List<TreeItem> itemList = ItemHelperFactory.createTreeItemList(newsItemBeens, null);
        TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter();
        treeRecyclerAdapter.setType(TreeRecyclerType.SHOW_ALL);
        treeRecyclerAdapter.setDatas(itemList);
        recyclerView.setAdapter(treeRecyclerAdapter);
    }
}
