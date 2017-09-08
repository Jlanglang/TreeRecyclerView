package com.baozi.treerecyclerview.base;

/**
 * Created by baozi on 2017/4/14.
 */

/**
 * javabean继承该类,后台返回的json中可以包含viewItemType,通过解析返回的viewItemType确定item样式
 */
public abstract class BaseItemData {

    private int viewItemType;

    public void setViewItemType(int viewItemType) {
        this.viewItemType = viewItemType;
    }

    public int getViewItemType() {
        return viewItemType;
    }
}
