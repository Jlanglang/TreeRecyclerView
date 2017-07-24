package com.baozi.treerecyclerview.item;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by baozi on 2016/12/22.
 * 可以选中子item的TreeItemGroup,点击的item会保存起来.可以通过 getSelectItems()获得选中item
 */
public abstract class TreeSelectItemGroup<D>
        extends TreeItemGroup<D> {
    /**
     * 选中的子item.只支持下一级,不支持下下级
     */
    private List<TreeItem> selectItems;

    public List<TreeItem> getSelectItems() {
        if (selectItems == null) {
            selectItems = new ArrayList<>();
        }
        return selectItems;
    }

    /**
     * 子级是否有选中
     *
     * @return
     */
    public boolean isChildCheck() {
        return !getSelectItems().isEmpty();
    }

    @Override
    public boolean onInterceptClick(TreeItem child) {
        addSelectItem(child);
        return super.onInterceptClick(child);
    }

    /**
     * 添加选中的Item;不建议直接调用该方法,
     * 当不需要用onInterceptClick()的时候,可以主动调用添加Item.
     * <
     * 如果onInterceptClick()生效,还主动调用该方法添加item,将无法添加.
     *
     * @param child
     */
    protected void addSelectItem(@NonNull TreeItem child) {
        if (selectFlag() == SelectFlag.SINGLE_CHOICE) {
            if (getSelectItems().size() != 0) {
                getSelectItems().set(0, child);
            } else {
                getSelectItems().add(child);
            }
        } else {
            int index = getSelectItems().indexOf(child);
            if (index == -1) {//不存在则添加
                getSelectItems().add(child);
            } else {//存在则添加
                getSelectItems().remove(index);
            }
        }
    }

    /**
     * 必须指定选中样式
     *
     * @return
     */
    public abstract SelectFlag selectFlag();

    /**
     * 决定TreeSelectItemGroup的选中样式
     */
    public enum SelectFlag {
        /**
         * 单选
         */
        SINGLE_CHOICE,
        /**
         * 多选
         */
        MULTIPLE_CHOICE
    }
}
