package com.baozi.treerecyclerview.widget

import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.adpater.TreeRecyclerType
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.item.TreeSortItem
import com.baozi.treerecyclerview.manager.ItemManager

import java.util.HashMap


/**
 * Created by Administrator on 2017/8/8 0008.
 * 索引adapter
 */

class TreeSortAdapter(type: TreeRecyclerType = TreeRecyclerType.SHOW_EXPAND) : TreeRecyclerAdapter(type) {
    private val sortMap = HashMap<Any?, TreeItem<*>?>()

    override val itemManager: TreeSortManageWrapper by lazy {
        TreeSortManageWrapper(this, super.itemManager)
    }

    override fun setData(data: List<TreeItem<*>>?) {
        super.setData(data)
        itemManager.updateSorts(getData())
    }


    fun getSortItem(o: Any?): TreeItem<*>? {
        return if (o == null) {
            null
        } else sortMap[o]
    }

    fun getSortIndex(o: Any): Int {
        val sortItem = getSortItem(o) ?: return -1
        return itemManager.getItemPosition(sortItem)
    }

    inner class TreeSortManageWrapper(adapter: BaseRecyclerAdapter<TreeItem<*>>, private var manager: ItemManager<TreeItem<*>>)
        : ItemManager<TreeItem<*>>(adapter) {
        override var isOpenAnim: Boolean
            get() = manager.isOpenAnim
            set(openAnim) {
                manager.isOpenAnim = openAnim
            }

        override fun addItem(item: TreeItem<*>) {
            manager.addItem(item)
            updateSort(manager.getItemPosition(item), item)
        }


        override fun addItem(position: Int, item: TreeItem<*>) {
            manager.addItem(position, item)
            updateSort(position, item)
        }

        override fun addItems(items: List<TreeItem<*>>) {
            manager.addItems(items)
            updateSorts(items)
        }

        override fun addItems(position: Int, items: List<TreeItem<*>>) {
            manager.addItems(position, items)
            updateSorts(items)
        }

        override fun removeItem(item: TreeItem<*>) {
            manager.removeItem(item)
            updateSort(getItemPosition(item), item)
        }

        override fun removeItem(position: Int) {
            val item = manager.getItem(position)
            if (item is TreeSortItem<*>) {
                sortMap.remove(item.sortKey)
            }
            manager.removeItem(position)
        }

        override fun removeItems(items: List<TreeItem<*>>) {
            manager.removeItems(items)
            val size = items.size
            for (i in 0 until size) {
                val item = items[i]
                if (item is TreeSortItem<*>) {
                    sortMap.remove(item.sortKey)
                }
            }
        }


        override fun replaceItem(position: Int, item: TreeItem<*>) {
            manager.replaceItem(position, item)
            updateSort(position, item)
        }

        override fun replaceAllItem(items: List<TreeItem<*>>) {
            manager.replaceAllItem(items)
            updateSorts(items)
        }

        override fun getItem(position: Int): TreeItem<*> {
            return manager.getItem(position)
        }

        override fun getItemPosition(item: TreeItem<*>): Int {
            return manager.getItemPosition(item)
        }

        override fun clean() {
            manager.clean()
        }

        fun updateSorts(treeItems: List<TreeItem<*>>) {
            val size = treeItems.size
            for (i in 0 until size) {
                val treeItem = treeItems[i]
                if (treeItem is TreeSortItem<*>) {
                    sortMap[treeItem.sortKey] = treeItem
                }
            }
        }

        fun updateSort(treeItem: TreeItem<*>) {
            if (treeItem is TreeSortItem<*>) {
                sortMap[treeItem.sortKey] = treeItem
            }
        }

        fun updateSort(position: Int, treeItem: TreeItem<*>) {
            if (treeItem is TreeSortItem<*>) {
                sortMap[treeItem.sortKey] = treeItem
            }
        }

        override fun dataToItemPosition(index: Int): Int {
            return manager.dataToItemPosition(index)
        }

        override fun itemToDataPosition(position: Int): Int {
            return manager.itemToDataPosition(position)
        }

        override fun notifyDataChanged() {
            manager.notifyDataChanged()
        }

        override fun addCheckItemInterfaces(itemInterface: CheckItemInterface) {
            manager.addCheckItemInterfaces(itemInterface)
        }
    }

}
