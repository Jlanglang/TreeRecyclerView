package com.baozi.treerecyclerview.item


import java.util.ArrayList


/**
 * Created by baozi on 2016/12/22.
 * 可以选中子item的TreeItemGroup,点击的item会保存起来.可以通过 selectItems获得选中item
 */
abstract class TreeSelectItemGroup<D> : TreeItemGroup<D>() {
    /**
     * 选中的子item.只保存当前的子级
     */
    val selectItems: MutableList<TreeItem<*>> = ArrayList()

    /**
     * 是否全选选中
     */
    val isSelectAll: Boolean
        get() = selectItems.containsAll(child!!)

    /**
     * 包含的子级是否有选中
     */
    val isSelect: Boolean
        get() = selectItems.isNotEmpty()


    /**
     * 选择全部，取消全部
     * 向下递归
     *
     * @param b            是否全部选中
     * @param isMultistage 是否多级关联
     */
    fun selectAll(b: Boolean, isMultistage: Boolean) {
        val child = child ?: return
        selectItems.clear()
        for (i in child.indices) {
            val item = child[i]
            if (item is TreeSelectItemGroup<*>) {
                item.selectAll(b)
            }
            if (b && !isSelect(item)) {
                selectItems.add(item)
            }
        }
        if (isMultistage) {
            updateParentSelect()
        }
    }

    /**
     * 选择全部，取消全部
     * 向下递归
     *
     * @param b 是否全部选中
     */
    fun selectAll(b: Boolean) {
        this.selectAll(b, false)
    }

    /**
     * 是否选中
     */
    fun isSelect(item: TreeItem<*>): Boolean {
        return selectItems.contains(item)
    }

    override fun onInterceptClick(child: TreeItem<*>): Boolean {
        parentItem?.apply {
            return onInterceptClick(this)
        }
        return super.onInterceptClick(child)
    }

    /**
     * 添加选中的Item;不建议直接调用该方法,
     * 当不需要用onInterceptClick()的时候,可以主动调用添加Item.
     * 如果onInterceptClick()生效,还主动调用该方法添加item,将无法添加.
     * 向上递归
     *
     * @param child        要添加选中的item
     * @param isMultistage 是否多级关联
     */
    protected fun selectItem(child: TreeItem<*>, isMultistage: Boolean) {
        if (selectFlag() == SelectFlag.SINGLE_CHOICE) {
            if (selectItems.size != 0) {
                selectItems[0] = child
            } else {
                selectItems.add(child)
            }
        } else {
            val index = selectItems.indexOf(child)
            if (index == -1) {//不存在则添加
                selectItems.add(child)
            } else {//存在则删除
                if (child is TreeSelectItemGroup<*>) {
                    if (child.isSelect) {
                        return
                    }
                }
                selectItems.removeAt(index)
            }
            if (isMultistage) {
                updateParentSelect()
            }
        }
    }

    protected fun selectItem(child: TreeItem<*>) {
        this.selectItem(child, false)
    }

    fun updateParentSelect() {
        val parentItem = parentItem
        if (parentItem is TreeSelectItemGroup<*>) {
            // 如果当前选中了,但是父类没有选中,则更新
            if (isSelect != parentItem.isSelect(this)) {
                parentItem.selectItem(this, true)
                parentItem.updateParentSelect()
            }
        }
    }

    /**
     * 默认多选
     *
     * @return
     */
    fun selectFlag(): SelectFlag {
        return SelectFlag.MULTIPLE_CHOICE
    }

    /**
     * 决定TreeSelectItemGroup的选中样式
     */
    enum class SelectFlag {
        /**
         * 单选
         */
        SINGLE_CHOICE,

        /**
         * 多选
         */
        MULTIPLE_CHOICE
    }

}
