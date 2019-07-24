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
     * 是否全选选中
     *
     * @return
     */
    public boolean isSelectAll() {
        return getSelectItems().containsAll(getChild());
    }


    /**
     * 选择全部，取消全部
     * 向下递归
     *
     * @param b
     */
    public void selectAll(boolean b) {
        List<TreeItem> child = getChild();
        if (child == null) {
            return;
        }
        getSelectItems().clear();
        for (int i = 0; i < child.size(); i++) {
            TreeItem treeItem = child.get(i);
            if (child.get(i) instanceof TreeSelectItemGroup) {
                ((TreeSelectItemGroup) child.get(i)).selectAll(b);
            }
            if (b && !isChildSelect(treeItem)) {
                getSelectItems().add(child.get(i));
            }
        }
        parentCheckSelect();
    }

    /**
     * 子级是否有选中
     *
     * @return
     */
    public boolean isChildSelect() {
        return !getSelectItems().isEmpty();
    }

    /**
     * 是否选中
     *
     * @return
     */
    public boolean isChildSelect(TreeItem item) {
        return getSelectItems().contains(item);
    }

    @Override
    public boolean onInterceptClick(TreeItem child) {
        selectItem(child);
        if (getParentItem() != null) {
            boolean b = getParentItem().onInterceptClick(this);
            if (b) {
                return true;
            }
        }
        return super.onInterceptClick(child);
    }

    /**
     * 添加选中的Item;不建议直接调用该方法,
     * 当不需要用onInterceptClick()的时候,可以主动调用添加Item.
     * 如果onInterceptClick()生效,还主动调用该方法添加item,将无法添加.
     * 向上递归
     */
    protected void selectItem(@NonNull TreeItem child) {
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
            } else {//存在则删除
                if (child instanceof TreeSelectItemGroup) {
                    if (((TreeSelectItemGroup) child).isChildSelect()) {
                        return;
                    }
                }
                getSelectItems().remove(index);
            }
        }
    }

    public void parentCheckSelect() {
        TreeItemGroup parentItem = getParentItem();
        if (parentItem instanceof TreeSelectItemGroup) {
            // 如果当前选中了,但是父类没有选中,则更新添加
            if (isChildSelect()!= ((TreeSelectItemGroup) parentItem).isChildSelect(this)) {
                ((TreeSelectItemGroup) parentItem).selectItem(this);
                ((TreeSelectItemGroup) parentItem).parentCheckSelect();
            }
        }
    }

    /**
     * 默认多选
     *
     * @return
     */
    public SelectFlag selectFlag() {
        return SelectFlag.MULTIPLE_CHOICE;
    }

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
