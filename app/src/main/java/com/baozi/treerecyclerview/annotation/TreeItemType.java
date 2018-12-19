package com.baozi.treerecyclerview.annotation;

import com.baozi.treerecyclerview.item.TreeItem;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by baozi on 2018/2/27.
 * 如果使用在item的类上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface TreeItemType {
    int type() default -1;
}
