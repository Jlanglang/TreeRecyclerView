package com.baozi.demo.item.mine;

import com.baozi.treerecyclerview.annotation.TreeDataType;

    @TreeDataType(iClass = MineCategoryItem.class)
    public class MineCategoryBean {
    public String title;
    public String url;//
    public String content;
    public boolean isEnd;

    /**
     * @param content 内容
     * @param url     图片，如果为null则隐藏
     * @param title   如果为null则隐藏
     */
    public MineCategoryBean(String content, String url, String title) {
        this(content, url, title, false);
    }

    /**
     * @param content 内容
     * @param url     图片，如果为null则隐藏
     * @param title   如果为null则隐藏
     * @param isEnd   是否是该组最后一排
     */
    public MineCategoryBean(String content, String url, String title, boolean isEnd) {
        this.title = title;
        this.url = url;
        this.content = content;
        this.isEnd = isEnd;
    }
}
