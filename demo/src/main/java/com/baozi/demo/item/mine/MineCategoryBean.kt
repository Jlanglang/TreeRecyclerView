package com.baozi.demo.item.mine

import com.baozi.treerecyclerview.annotation.TreeDataType

@TreeDataType(iClass = MineCategoryItem::class)
class MineCategoryBean
/**
 * @param content 内容
 * @param url     图片，如果为null则隐藏
 * @param title   如果为null则隐藏
 * @param isEnd   是否是该组最后一排
 */
@JvmOverloads constructor(var content: String?, var url: String?//
                          , var title: String?, var isEnd: Boolean = false)
/**
 * @param content 内容
 * @param url     图片，如果为null则隐藏
 * @param title   如果为null则隐藏
 */
