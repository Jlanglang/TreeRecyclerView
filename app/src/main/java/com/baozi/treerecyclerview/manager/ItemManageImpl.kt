package com.baozi.treerecyclerview.manager

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter

/**
 * 默认使用 notifyDataChanged();刷新.
 * 如果使用带动画效果的,条目过多可能会出现卡顿.
 */
open class ItemManageImpl<T>(adapter: BaseRecyclerAdapter<T>) : ItemManager<T>(adapter) {
    protected var data: MutableList<T>
        get() = adapter.getData()
        set(items) {
            adapter.setData(items)
        }

    override fun addItem(item: T) {
        data.add(item)
        if (!isOpenAnim) {
            notifyDataChanged()
            return
        }
        val itemPosition = getItemPosition(item)
        adapter.notifyItemInserted(itemPosition)
    }

    override fun addItem(position: Int, item: T) {
        data.add(position, item)
        if (!isOpenAnim) {
            notifyDataChanged()
            return
        }
        adapter.notifyItemInserted(dataToItemPosition(position))
    }


    override fun addItems(items: List<T>) {
        data.addAll(items)
        if (!isOpenAnim) {
            notifyDataChanged()
            return
        }
        adapter.notifyItemRangeInserted(data.size, items.size)
    }

    override fun addItems(position: Int, items: List<T>) {
        data.addAll(position, items)
        if (!isOpenAnim) {
            notifyDataChanged()
            return
        }
        val itemPosition = dataToItemPosition(position)
        adapter.notifyItemRangeInserted(itemPosition, items.size)
    }

    override fun removeItem(item: T) {
        val position = getItemPosition(item)
        data.remove(item)
        if (!isOpenAnim) {
            notifyDataChanged()
            return
        }
        val itemPosition = dataToItemPosition(position)
        adapter.notifyItemRemoved(itemPosition)
    }

    override fun removeItem(position: Int) {
        data.removeAt(position)
        if (!isOpenAnim) {
            notifyDataChanged()
            return
        }
        val itemPosition = dataToItemPosition(position)
        adapter.notifyItemRemoved(itemPosition)
    }

    override fun removeItems(items: List<T>) {
        data.removeAll(items)
        notifyDataChanged()
    }

    override fun replaceItem(position: Int, item: T) {
        data[position] = item
        if (!isOpenAnim) {
            notifyDataChanged()
            return
        }
        val itemPosition = dataToItemPosition(position)
        adapter.notifyItemChanged(itemPosition)
    }

    override fun replaceAllItem(items: List<T>) {
        data = items.toMutableList()
        if (!isOpenAnim) {
            notifyDataChanged()
            return
        }
        adapter.notifyItemRangeChanged(0, items.size)
    }

    override fun getItem(position: Int): T {
        return data[position]
    }

    override fun getItemPosition(item: T): Int {
        return data.indexOf(item)
    }

    override fun clean() {
        adapter.clear()
        notifyDataChanged()
    }
}