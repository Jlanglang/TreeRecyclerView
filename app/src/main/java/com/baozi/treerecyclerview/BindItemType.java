package com.baozi.treerecyclerview;

import com.baozi.treerecyclerview.item.TreeItem;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 125C01063144 on 2018/2/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindItemType {
    /**
     * -1代表不注册，请勿使用该值
     *
     * @return
     */
    int type() default -1;

    Class itemClass() default Object.class;
}
