package com.baozi.demo.item.cart;

import com.baozi.treerecyclerview.annotation.TreeDataType;

@TreeDataType(iClass = CartGroupItem3.class)
public class CartBean3 {
    int childSum; //子条目数量

    public CartBean3(int childSum) {
        this.childSum = childSum;
    }
}
