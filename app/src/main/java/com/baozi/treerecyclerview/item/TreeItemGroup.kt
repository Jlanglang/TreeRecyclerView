package com.baozi.treerecyclerview.item

import com.baozi.treerecyclerview.adpater.TreeRecyclerType
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.factory.ItemHelperFactory

/**
 * Created by baozi on 2016/12/22.
 * //拥有子集
 * //子集可以是parent,也可以是child
 * //可展开折叠
 */

abstract class TreeItemGroup<D> : TreeItem<D>() {
    /**
     * 持有的子item
     */
    /**
     * 获得自己的childs.
     *
     * @return
     */
    var child: List<TreeItem<*>>? = null
        private set

    /**
     * 是否展开
     */
    var isExpand: Boolean = false
        set(value) {
            if (!isCanExpand) {
                return
            }
            if (field == value) {//防止重复展开
                return
            }
            field = value
            if (field) {
                onExpand()
            } else {
                onCollapse()
            }
        }
    /**
     * 是否能展开
     */
    /**
     * 能否展开折叠
     *
     */
    open var isCanExpand = true


    /**
     * 获得所有展开的childs,包括子item的childs
     *
     * @return
     */
    val expandChild: List<TreeItem<*>>
        get() = ItemHelperFactory.getChildItemsWithType(this, TreeRecyclerType.SHOW_EXPAND)

    /**
     * 获得所有childs,包括下下....级item的childs
     *
     * @return
     */
    val allChilds: List<TreeItem<*>>?
        get() = ItemHelperFactory.getChildItemsWithType(this, TreeRecyclerType.SHOW_ALL)


    val childCount: Int
        get() = if (child == null) 0 else child!!.size

//    fun isExpand(): Boolean {
//        return isExpand
//    }

//    /**
//     * 设置为传入
//     *
//     * @param expand 传入true则展开,传入false则折叠
//     */
//    fun setExpand(expand: Boolean) {
//
//    }

    /**
     * 展开
     */
    open fun onExpand() {
        val itemManager = itemManager ?: return
        val child = this.child
        if (child == null || child.isEmpty()) {
            isExpand = false
            return
        }
        val itemPosition = itemManager.getItemPosition(this)
        itemManager.addItems(itemPosition + 1, child)
    }

    /**
     * 折叠
     */
    open fun onCollapse() {
        val itemManager = itemManager ?: return
        val child = this.child
        if (child.isNullOrEmpty()) {
            isExpand = false
            return
        }
        itemManager.removeItems(child)
    }

    override var data: D? = null
        set(value) {
            field = value
            value ?: return
            child = initChild(value)
        }

    /**
     * 初始化子集
     *
     * @param data
     * @return
     */
    protected open fun initChild(data: D): List<TreeItem<*>>? {
        return null
    }

    /**
     * 是否消费child的click事件
     *
     * @param child 具体click的item
     * @return 返回true代表消费此次事件，child不会走onclick()，返回false说明不消费此次事件，child依然会走onclick()
     */
    open fun onInterceptClick(child: TreeItem<*>): Boolean {
        return false
    }

    override fun onClick(viewHolder: ViewHolder) {
        super.onClick(viewHolder)
        //必须是TreeItemGroup才能展开折叠,并且type不能为 TreeRecyclerType.SHOW_ALL
        isExpand =!isExpand
    }
}
