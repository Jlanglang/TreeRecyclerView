package com.baozi.treerecyclerview.adpater;

public enum TreeRecyclerType {
    /**
     * 显示所有,不可展开折叠
     * 适用场景,不需要折叠，默认显示所有item
     */
    SHOW_ALL,

    /**
     * 根据isExpand的状态显示展开与折叠,
     * 适用场景,多级的data数据展示,保存展开状态
     */
    SHOW_EXPAND,

    /**
     * 默认只显示第一级,点击展开,折叠不会影响子级展开折叠
     * 适用场景,一级一级展开，保存展开状态
     */
    SHOW_DEFAULT
}
