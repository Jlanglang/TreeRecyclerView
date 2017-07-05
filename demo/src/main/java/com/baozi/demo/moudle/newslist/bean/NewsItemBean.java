package com.baozi.demo.moudle.newslist.bean;

import com.baozi.treerecyclerview.base.BaseItemData;

import java.util.List;

/**
 * @author jlanglang  2017/7/5 17:34
 * @版本 2.0
 * @Change
 */
public class NewsItemBean extends BaseItemData {
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

    public static class NewsImageBean extends BaseItemData {

    }

    public static class NewsFootBean extends BaseItemData {

    }

}
