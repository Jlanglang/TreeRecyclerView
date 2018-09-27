package com.baozi.demo.bean;

import com.baozi.demo.item.news.NewsImageItem;
import com.baozi.demo.item.news.NewsItem;
import com.baozi.treerecyclerview.annotation.TreeDataType;

/**
 * @author jlanglang  2017/7/5 17:34
 * @版本 2.0
 * @Change
 */
@TreeDataType(iClass = NewsItem.class)
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

    @TreeDataType(iClass = NewsImageItem.class)
    public static class NewsImageBean {

    }

}
