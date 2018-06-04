package com.baozi.treerecyclerview.annotation;

import com.baozi.treerecyclerview.item.TreeItem;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 125C01063144 on 2018/2/27.
 * itemClass与filedName冲突,只生效一个,
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TreeItemClass {
    /**
     * 直接绑定itemclass
     *
     * @return
     */
    Class iClass() default Object.class;

    /**
     * 参数名,用来获取对应的参数,解析值,获得type
     *
     * @return
     */
    String filedName() default "";
}
