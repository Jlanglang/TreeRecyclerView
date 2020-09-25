package com.baozi.treerecyclerview.item

/**
 * Created by Administrator on 2017/8/8 0008.
 */

abstract class TreeSortItem<T> : TreeItemGroup<T>() {
    var sortKey: Any? = null

}
