package com.baozi.demo.item.cart;

import com.baozi.treerecyclerview.annotation.TreeDataType;


@TreeDataType(iClass = CartGroupItem.class)
public class CartBean {
    int childSum; //子条目数量

    public CartBean(int childSum) {
        this.childSum = childSum;
    }

}
