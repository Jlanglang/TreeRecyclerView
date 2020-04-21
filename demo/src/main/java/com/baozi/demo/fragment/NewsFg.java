package com.baozi.demo.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.baozi.demo.item.news.NewsItemBean;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jlanglang  2017/7/5 10:22
 * @版本 2017-8-19;有点勉强,不建议这样使用- -
 */

public class NewsFg extends SimpleRecyclerViewFg {
    protected TreeRecyclerAdapter adapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 6));
        recyclerView.setAdapter(adapter);
        ArrayList<NewsItemBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NewsItemBean newsItemBean = new NewsItemBean();
            newsItemBean.title = "123";
            newsItemBean.images = new Random().nextInt(9) + 1;
            list.add(newsItemBean);
        }
        List<TreeItem> itemList = ItemHelperFactory.createItems(list);
        adapter.getItemManager().replaceAllItem(itemList);
    }
}
