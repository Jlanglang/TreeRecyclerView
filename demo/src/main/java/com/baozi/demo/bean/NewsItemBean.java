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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    private int images;

    @TreeItemClass(iClass = NewsImageItem.class)
    public static class NewsImageBean {

    }

}
