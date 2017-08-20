package com.baozi.treerecyclerview.adpater;

public enum TreeRecyclerType {
    /**
     * 显示所有,不可展开折叠
     * 适用场景,多级的data数据展示,多type布局.
     */
    SHOW_ALL,
    /**
     * 根据isExpand的状态显示展开与折叠,
     * 适用场景,多级的data数据展示,多type布局.
     */
    SHOW_EXPAND,

    /**
     * 默认显示,显示一级,点击展开,折叠不会影响子级展开折叠
     * 适用场景,多级列表,保存展开状态
     */
    SHOW_DEFUTAL;
}
