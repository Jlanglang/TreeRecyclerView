package com.baozi.treerecyclerview.annotation

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

/**
 * Created by 125C01063144 on 2018/2/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class TreeDataType(
        /**
         * 要绑定的item
         */
        val iClass: KClass<*> = Any::class,
        val bindField: String = ""
)

