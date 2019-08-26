package com.baozi.demo.item.cart;

import com.baozi.treerecyclerview.annotation.TreeDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TreeDataType(iClass = CartGroupItem.class)
public class CartBean {
    int childSum; //子条目数量

    public CartBean(int childSum) {
        this.childSum = childSum;
    }

}
