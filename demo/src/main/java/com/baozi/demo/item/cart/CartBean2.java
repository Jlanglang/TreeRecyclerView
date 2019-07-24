package com.baozi.demo.item.cart;

import com.baozi.treerecyclerview.annotation.TreeDataType;

@TreeDataType(iClass = CartGroupItem2.class)
public class CartBean2 {
    int childSum; //子条目数量

    public CartBean2(int childSum) {
        this.childSum = childSum;
    }
}
