package com.baozi.treerecyclerview.annotation


import android.support.annotation.LayoutRes
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by baozi on 2018/2/27.
 * 如果使用在item的类上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class TreeItemType(
        /**
         * 映射的type值
         *
         * @return [type1, type2]
         */
        val type: Array<String>,
        /**
         * item的列占比
         *
         * @return 默认返回0, 也就是不设置
         */
        val spanSize: Int = 0)
