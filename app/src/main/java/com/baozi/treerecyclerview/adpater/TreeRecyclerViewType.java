package com.baozi.treerecyclerview.adpater;

public enum TreeRecyclerViewType {
    /**
     * 显示所有,不可展开折叠
     */
    SHOW_ALL,
    /**
     * 默认显示一级,点击展开一级,折叠会使子级折叠
     */
    SHOW_COLLAPSE_CHILDS,
    /**
     * 默认显示,显示一级,点击展开,折叠不会影响子级展开折叠
     */
    SHOW_DEFUTAL,
    /**
     * 根据item的设置,决定是否可以展开折叠.
     */
    SHOW_ITEM;

    TreeRecyclerViewType() {
    }
}
