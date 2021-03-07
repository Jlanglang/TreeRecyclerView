package com.baozi.treerecyclerview.factory

import android.util.Log
import com.baozi.treerecyclerview.adpater.TreeRecyclerType
import com.baozi.treerecyclerview.annotation.TreeItemType
import com.baozi.treerecyclerview.item.TreeItem
import com.baozi.treerecyclerview.item.TreeItemGroup
import java.util.*

/**
 * Created by baozi on 2017/4/29.
 */

object ItemHelperFactory {
    private val classCacheMap = HashMap<Class<*>, Class<out TreeItem<*>>>()

    /**
     * 创建Item
     */
    fun createItems(list: List<*>? = null,
                    iClass: Class<out TreeItem<*>>? = null,
                    treeParentItem: TreeItemGroup<*>? = null): MutableList<TreeItem<*>> {
        list ?: return ArrayList()
        return list.mapNotNull { createItem(it, iClass, treeParentItem) }.toMutableList()
    }

    /**
     * 确定item的class类型,并且添加到了itemConfig,用该方法创建TreeItem
     *
     * @return
     */
    fun createItem(data: Any?, zClass: Class<out TreeItem<*>>? = null, treeParentItem: TreeItemGroup<*>? = null): TreeItem<*>? {
        var treeItem: TreeItem<Any>? = null
        val treeItemClass: Class<out TreeItem<*>>?
        try {
            treeItemClass = if (zClass != null) {
                ItemConfig.register(zClass)
                zClass
            } else {
                ItemConfig.getTypeClass(data)
            }
            //判断是否是TreeItem的子类
            if (treeItemClass != null) {
                treeItem = treeItemClass.newInstance() as? TreeItem<Any>
                treeItem ?: return null
                treeItem.data = data
                treeItem.parentItem = treeParentItem
                val annotation = treeItemClass.getAnnotation<TreeItemType>(TreeItemType::class.java)
                if (annotation != null) {
                    treeItem.spanSize = annotation.spanSize
                }
            }
        } catch (e: ClassCastException) {
            Log.w("ClassCastException", "传入的data与item定义的data泛型不一致")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return treeItem
    }


    /**
     * 根据TreeRecyclerType获取子item集合,不包含TreeItemGroup自身
     *
     * @param itemGroup
     * @param type
     * @return
     */
    fun getChildItemsWithType(itemGroup: TreeItemGroup<*>?, type: TreeRecyclerType): ArrayList<TreeItem<*>> {
        itemGroup ?: return ArrayList()
        return getChildItemsWithType(itemGroup.child, type)
    }

    fun getChildItemsWithType(items: List<TreeItem<*>>?, type: TreeRecyclerType): ArrayList<TreeItem<*>> {
        val returnItems = ArrayList<TreeItem<*>>()
        items ?: return returnItems
        val childCount = items.size
        for (i in 0 until childCount) {
            val childItem = items[i]//获取当前一级
            returnItems.add(childItem)
            if (childItem is TreeItemGroup<*>) {//获取下一级
                var list: List<TreeItem<*>>? = null
                when (type) {
                    TreeRecyclerType.SHOW_ALL ->
                        //调用下级的getAllChild遍历,相当于递归遍历
                        list = getChildItemsWithType(childItem, type)
                    TreeRecyclerType.SHOW_EXPAND ->
                        //根据isExpand,来决定是否展示
                        if (childItem.isExpand) {
                            list = getChildItemsWithType(childItem, type)
                        }
                    else -> {

                    }
                }
                if (!list.isNullOrEmpty()) {
                    returnItems.addAll(list)
                }
            }
        }
        return returnItems
    }
}
