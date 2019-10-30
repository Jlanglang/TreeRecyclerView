package com.baozi.demo.item.cart;

import com.baozi.treerecyclerview.annotation.TreeDataType;


@TreeDataType(iClass = CartGroupItem.class)
public class CartBean {
    int childSum; //子条目数量

    public CartBean(int childSum) {
        this.childSum = childSum;
    }

    @TreeDataType(iClass = CartGroupItem2.class)
    public static class CartBean2 {
        int childSum; //子条目数量

        public CartBean2(int childSum) {
            this.childSum = childSum;
        }

        @TreeDataType(iClass = CartGroupItem3.class)
        public static class CartBean3 {
            int childSum; //子条目数量

            public CartBean3(int childSum) {
                this.childSum = childSum;
            }
        }
    }
}
