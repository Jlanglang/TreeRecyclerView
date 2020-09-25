package com.baozi.treerecyclerview.factory


import com.baozi.treerecyclerview.annotation.TreeItemType
import com.baozi.treerecyclerview.item.TreeItem

import java.util.HashMap


object ItemConfig {

    private val treeViewHolderTypes = HashMap<String, Class<out TreeItem<*>>>()

    fun getTreeViewHolderType(type: String): Class<out TreeItem<*>>? {
        return treeViewHolderTypes[type]
    }

    fun register(type: String, clazz: Class<out TreeItem<*>>?) {
        if (null == clazz) {
            return
        }
        treeViewHolderTypes[type] = clazz
    }

    fun register(zClass: Class<out TreeItem<*>>) {
        val annotation = zClass.getAnnotation(TreeItemType::class.java)
        if (annotation != null) {
            val typeList = annotation.type
            typeList.forEach {
                val typeClass = treeViewHolderTypes[it]
                if (typeClass == null) {
                    treeViewHolderTypes[it] = zClass
                    return@forEach
                }
                check(zClass == typeClass) {
                    //如果该type,已经添加了,则抛出异常
                    "该type映射了一个TreeItemClass,不能再映射其他TreeItemClass"
                }
            }
        }
    }

    @SafeVarargs
    fun register(vararg clazz: Class<out TreeItem<*>>) {
        for (zClass in clazz) {
            register(zClass)
        }
    }

}