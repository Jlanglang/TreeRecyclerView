package com.baozi.treerecyclerview.view;

public interface TreeItemManager<T extends BaseItem> {

    void addView(T view);

    void updateView();

    void removeView(T view);


}