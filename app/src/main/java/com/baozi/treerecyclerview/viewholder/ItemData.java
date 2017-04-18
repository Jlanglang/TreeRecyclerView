package com.baozi.treerecyclerview.viewholder;

/**
 * Created by baozi on 2017/4/14.
 */

/**
 * 继承该类,后台返回json中需包含viewItemType,通过viewItemType确定item样式
 */
public abstract class ItemData {
    private int viewItemType;

    public int getViewItemType() {
        return viewItemType;
    }

    public void setViewItemType(int viewItemType) {
        this.viewItemType = viewItemType;
    }
}
