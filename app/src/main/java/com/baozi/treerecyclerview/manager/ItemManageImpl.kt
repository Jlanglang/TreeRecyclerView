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
        checkAnim {
            val itemPosition = getItemPosition(item)
            adapter.notifyItemInserted(itemPosition)
        }
    }

    override fun addItem(position: Int, item: T) {
        data.add(position, item)
        checkAnim {
            adapter.notifyItemInserted(dataToItemPosition(position))
        }
    }

    override fun addItems(items: List<T>) {
        data.addAll(items)
        checkAnim {
            adapter.notifyItemRangeInserted(data.size, items.size)
        }
    }

    override fun addItems(position: Int, items: List<T>) {
        data.addAll(position, items)
        checkAnim {
            val itemPosition = dataToItemPosition(position)
            adapter.notifyItemRangeInserted(itemPosition, items.size)
        }

    }

    override fun removeItem(item: T) {
        val position = getItemPosition(item)
        data.remove(item)
        checkAnim {
            val itemPosition = dataToItemPosition(position)
            adapter.notifyItemRemoved(itemPosition)
        }
    }

    override fun removeItem(position: Int) {
        data.removeAt(position)
        checkAnim {
            val itemPosition = dataToItemPosition(position)
            adapter.notifyItemRemoved(itemPosition)
        }
    }

    override fun removeItems(items: List<T>) {
        data.removeAll(items)
        notifyDataChanged()
    }

    override fun replaceItem(position: Int, item: T) {
        data[position] = item
        checkAnim {
            val itemPosition = dataToItemPosition(position)
            adapter.notifyItemChanged(itemPosition)
        }
    }

    override fun replaceAllItem(items: List<T>) {
        data = items.toMutableList()
        checkAnim {
            adapter.notifyItemRangeChanged(0, items.size)
        }
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

    private inline fun checkAnim(block: () -> Unit) {
        if (isOpenAnim) {
            block()
            return
        }
        notifyDataChanged()
    }
}