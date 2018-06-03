package com.baozi.treerecyclerview;

import com.baozi.treerecyclerview.item.TreeItem;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 125C01063144 on 2018/2/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindItemClass {
    Class itemClass() default Object.class;

    String filedName() default "";
}
