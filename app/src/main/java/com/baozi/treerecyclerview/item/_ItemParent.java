package com.baozi.treerecyclerview.item;


import java.util.List;

/**
 * @author jlanglang  2016/12/23 17:04
 * @版本 2.0
 * @Change
 */
interface _ItemParent {
    /**
     * 是否允许展开
     *
     * @return
     */
    boolean isCanExpand();

    /**
     * 展开后的回调
     */
    void onExpand();

    /**
     * 折叠后的回调
     */
    void onCollapse();

    /**
     * 获取子集
     *
     * @return
     */
    List<? extends TreeItem> getChilds();

}
