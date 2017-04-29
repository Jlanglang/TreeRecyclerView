package com.baozi.treerecyclerview.adpater;

public enum TreeRecyclerViewType {
    /**
     * 显示所有,不可展开折叠
     * 适用场景,多级的data数据展示多type布局.
     */
    SHOW_ALL,
    /**
     * 显示所有,可展开折叠
     * 适用场景,多级的data数据展示多type布局.
     */
    EXPAND_SHOW_ALL,

    /**
     * 默认显示一级,点击展开一级,折叠会使子级折叠
     * 适用场景,多级列表,不保存展开状态
     */
    SHOW_COLLAPSE_CHILDS,

    /**
     * 默认显示,显示一级,点击展开,折叠不会影响子级展开折叠
     * 适用场景,多级列表,保存展开状态
     */
    SHOW_DEFUTAL;
}
