package com.baozi.treerecyclerview.factory


import com.baozi.treerecyclerview.annotation.TreeDataType
import com.baozi.treerecyclerview.annotation.TreeItemType
import com.baozi.treerecyclerview.item.TreeItem
import java.util.*


object ItemConfig {

    private val treeViewHolderTypes = HashMap<String, Class<out TreeItem<*>>>()

    /**
     * TreeDataType映射
     */
    private val treeDataTypeMap = HashMap<Class<*>, TreeDataType>()

    /**
     * TreeItemType映射
     */
    private val treeItemTypeMap = HashMap<Class<*>, TreeItemType>()

    fun getTreeViewHolderType(type: String): Class<out TreeItem<*>>? {
        return treeViewHolderTypes[type]
    }

    fun register(type: String, clazz: Class<out TreeItem<*>>?) {
        clazz ?: return
        val typeClass = treeViewHolderTypes[type]
        if (typeClass == null) {
            treeViewHolderTypes[type] = clazz
            return
        }
        check(clazz == typeClass) {
            //如果该type,已经添加了,则抛出异常
            "该type映射了一个TreeItemClass,不能再映射其他TreeItemClass"
        }
    }

    fun register(zClass: Class<out TreeItem<*>>) {
        treeItemTypeMap[zClass] ?: return
        val annotation = zClass.getAnnotation(TreeItemType::class.java)
        if (annotation != null) {
            treeItemTypeMap[zClass] = annotation
            val typeList = annotation.type
            typeList.forEach {
                register(it, zClass)
            }
        }
    }

    @SafeVarargs
    fun register(vararg clazz: Class<out TreeItem<*>>) {
        for (zClass in clazz) {
            register(zClass)
        }
    }

    /**
     * 获取TreeItem的Class
     *
     * @param data
     * @return
     */
    fun getTypeClass(data: Any?): Class<out TreeItem<*>>? {
        data ?: return null
        val dataClass = data.javaClass
        //先判断是否继承了ItemData,适用于跨模块获取
        //判断是否使用注解绑定了ItemClass,适用当前模块
        val treeDataType = treeDataTypeMap[dataClass]
                ?: dataClass.getAnnotation(TreeDataType::class.java)
                ?: return null
        //缓存注解
        treeDataTypeMap[dataClass] = treeDataType
        val key = treeDataType.bindField
        if (key.isNotEmpty()) {
            try {
                val field = dataClass.getField(key)
                val type = field.get(data).toString()
                return getTreeViewHolderType(type)
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return treeDataType.iClass.java as Class<out TreeItem<*>>?
        }
        return null
    }
}