package com.baozi.treerecyclerview.view;

/**
 * Created by baozi on 2017/4/14.
 */

/**
 * 继承该类,后台返回的json中需包含viewItemType,通过viewItemType确定item样式
 */
public interface ItemData {
    int getViewItemType();
}
