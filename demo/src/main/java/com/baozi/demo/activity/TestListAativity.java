package com.baozi.demo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.demo.moudle.testlist.ContentImageItem;
import com.baozi.demo.moudle.testlist.bean.ContentBean;
import com.baozi.demo.moudle.testlist.SelectionImageItem;
import com.baozi.demo.moudle.testlist.SelectionTextItem;
import com.baozi.demo.moudle.testlist.bean.TitleBean;
import com.baozi.demo.moudle.testlist.ContentGroupItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.factory.ItemConfig;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/4/27.
 */

public class TestListAativity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testlist);
        ItemConfig.addTreeHolderType(102, ContentImageItem.class);
        ItemConfig.addTreeHolderType(100, ContentGroupItem.class);
        ItemConfig.addTreeHolderType(101, SelectionImageItem.class);
        ItemConfig.addTreeHolderType(103, SelectionTextItem.class);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rl_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 10;
                if (view.getLayoutParams() instanceof GridLayoutManager.LayoutParams) {
                    GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                    int spanIndex = layoutParams.getSpanIndex();//在一行中所在的角标，第几列
                    if (spanIndex != ((GridLayoutManager) parent.getLayoutManager()).getSpanCount() - 1) {
                        outRect.right = 10;
                    }
                }
            }
        });
        ArrayList<TitleBean> titleBeens = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            TitleBean titleBean = new TitleBean();
            titleBean.setViewItemType(100);
            if (i % 5 == 0) {//图片的选项
                ArrayList<ContentBean> singleBeens = new ArrayList<>();
                if (i % 10 == 0) {
                    ContentBean singleBean = new ContentBean();
                    singleBean.setType("image");
                    singleBean.setViewItemType(102);
                    singleBeens.add(singleBean);
                }
                for (int j = 0; j < 4; j++) {
                    ContentBean singleBean = new ContentBean();
                    singleBean.setType("Contnet_image");
                    singleBean.setViewItemType(101);
                    singleBeens.add(singleBean);
                }

                titleBean.setSingleBeen(singleBeens);
                titleBeens.add(titleBean);
            } else {//文字的选项
                ArrayList<ContentBean> singleBeens = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    ContentBean singleBean = new ContentBean();
                    singleBean.setType("text");
                    singleBean.setNumber(j + ".");
                    singleBean.setViewItemType(103);
                    singleBeens.add(singleBean);
                }
                titleBean.setSingleBeen(singleBeens);
                titleBeens.add(titleBean);
            }
        }
        List<TreeItem> itemList = ItemHelperFactory.createTreeItemList(titleBeens,null);
        TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter();
        treeRecyclerAdapter.setType(TreeRecyclerType.SHOW_ALL);
        treeRecyclerAdapter.setDatas(itemList);
        recyclerView.setAdapter(treeRecyclerAdapter);
    }
}
