package com.baozi.treerecyclerview.viewholder;

import java.util.List;

/**
 * @author jlanglang  2016/12/23 17:04
 * @版本 2.0
 * @Change
 */
public interface ParentItem {

    boolean isExpand();

    void onExpand();

    void onCollapse();

    List<TreeItem> getChilds();

    List<TreeItem> getAllChilds();
}
