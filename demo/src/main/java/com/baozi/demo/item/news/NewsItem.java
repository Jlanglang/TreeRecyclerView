package com.baozi.demo.item.news;

import android.graphics.Rect;
import android.support.annotation.NonNull;

import com.baozi.demo.R;
import com.baozi.demo.bean.NewsItemBean;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.SimpleTreeItem;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jlanglang  2017/7/5 17:15
 * @版本 2.0
 * @Change
 */
//新闻列表,一级父
public class NewsItem extends TreeItemGroup<NewsItemBean> {
    @Override
    protected List<TreeItem> initChildList(NewsItemBean data) {
        //添加图片
        int images = data.getImages();
        ArrayList<NewsItemBean.NewsImageBean> childs = new ArrayList<>();
        for (int i = 0; i < images; i++) {
            NewsItemBean.NewsImageBean newsImageItem = new NewsItemBean.NewsImageBean();
            childs.add(newsImageItem);
        }
        List<TreeItem> treeItemList = ItemHelperFactory.createItems(childs, this);

        //添加尾部
        treeItemList.add(new SimpleTreeItem(R.layout.item_news_foot)
                .setTreeClick(new SimpleTreeItem.Consumer<ViewHolder>() {
                    @Override
                    public void accept(ViewHolder viewHolder) {
                        //点击跳转
                    }
                }).setTreeOffset(new Rect(0, 0, 0, 20)));
        return treeItemList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_news_title;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        viewHolder.setText(R.id.tv_title, data.getTitle());
    }


//    @Override
//    public void setData(NewsItemBean data) {
//        if (getData() != null) {
//            super.setData(data);
//            setExpand(true);
//            return;
//        }
//        super.setData(data);
//    }
}
