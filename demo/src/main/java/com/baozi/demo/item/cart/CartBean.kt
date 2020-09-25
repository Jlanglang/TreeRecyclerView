package com.baozi.demo.item.cart

import com.baozi.treerecyclerview.annotation.TreeDataType


@TreeDataType(bindField = "type")
class CartBean(internal var childSum: Int //子条目数量
) {
    @JvmField
    var type = 1

    @TreeDataType(iClass = CartGroupItem2::class)
    class CartBean2(internal var childSum: Int //子条目数量
    ) {

        @TreeDataType(iClass = CartGroupItem3::class)
        class CartBean3(internal var childSum: Int //子条目数量
        )
    }
}
