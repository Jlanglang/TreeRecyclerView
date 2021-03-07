package com.baozi.treerecyclerview.manager


import com.baozi.treerecyclerview.base.BaseRecyclerAdapter

import java.util.ArrayList

/**
 * 条目增删管理类
 *
 * @param <T>
</T> */
abstract class ItemManager<T>(var adapter: BaseRecyclerAdapter<T>) {
    open var isOpenAnim: Boolean = false
    private var checkItemInterfaces: ArrayList<CheckItemInterface>? = null
    var tag: Any? = null

    //增
    abstract fun addItem(item: T)

    abstract fun addItem(position: Int, item: T)

    abstract fun addItems(items: List<T>)

    abstract fun addItems(position: Int, items: List<T>)

    //删
    abstract fun removeItem(item: T)

    abstract fun removeItem(position: Int)

    abstract fun removeItems(items: List<T>)


    //改
    abstract fun replaceItem(position: Int, item: T)

    abstract fun replaceAllItem(items: List<T>)

    //查
    abstract fun getItem(position: Int): T

    abstract fun getItemPosition(item: T): Int

    abstract fun clean()

    //刷新
    open fun notifyDataChanged() {
        adapter.notifyDataSetChanged()
    }


    /**
     * 检查item属性
     */
    interface CheckItemInterface {
        fun itemToDataPosition(position: Int): Int

        fun dataToItemPosition(index: Int): Int
    }

    open fun removeCheckItemInterfaces(itemInterface: CheckItemInterface) {
        checkItemInterfaces?.remove(itemInterface)
    }

    open fun addCheckItemInterfaces(itemInterface: CheckItemInterface) {
        if (checkItemInterfaces == null) {
            checkItemInterfaces = ArrayList()
        }
        checkItemInterfaces?.add(itemInterface)
    }

    /**
     * 解决holder角标与data的角标不一致问题。
     *
     * @param position
     * @return
     */
    open fun itemToDataPosition(position: Int): Int {
        var tmp = position
        checkItemInterfaces?.forEach {
            tmp = it.itemToDataPosition(tmp)
        }
        return tmp
    }

    /**
     * 解决data的角标与holder角标不一致问题。
     *
     * @param index
     * @return
     */
    open fun dataToItemPosition(index: Int): Int {
        var tmp = index
        checkItemInterfaces?.forEach {
            tmp = it.dataToItemPosition(tmp)
        }
        return tmp
    }
}