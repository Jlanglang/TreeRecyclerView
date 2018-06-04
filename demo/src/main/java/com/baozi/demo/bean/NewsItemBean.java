package com.baozi.demo.bean;

import com.baozi.demo.item.news.NewsFootItem;
import com.baozi.demo.item.news.NewsImageItem;
import com.baozi.demo.item.news.NewsItem;
import com.baozi.treerecyclerview.annotation.TreeItemClass;

import java.util.List;

/**
 * @author jlanglang  2017/7/5 17:34
 * @版本 2.0
 * @Change
 */
@TreeItemClass(iClass = NewsItem.class)
public class NewsItemBean {
    private String title;

    public NewsFootBean getFootBean() {
        return mFootBean;
    }

    public void setFootBean(NewsFootBean footBean) {
        mFootBean = footBean;
    }

    public List<NewsImageBean> getImageBeanList() {
        return mImageBeanList;
    }

    public void setImageBeanList(List<NewsImageBean> imageBeanList) {
        mImageBeanList = imageBeanList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private NewsFootBean mFootBean;
    private List<NewsImageBean> mImageBeanList;

    @TreeItemClass(iClass = NewsImageItem.class)
    public static class NewsImageBean {

    }

    @TreeItemClass(iClass = NewsFootItem.class)
    public static class NewsFootBean {

    }

}
