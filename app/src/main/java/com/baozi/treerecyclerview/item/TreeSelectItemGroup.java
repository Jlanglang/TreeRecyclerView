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
    public void selectAll(boolean b, boolean isMultistage) {
        List<TreeItem> child = getChild();
        if (child == null) {
            return;
        }
        getSelectItems().clear();
        for (int i = 0; i < child.size(); i++) {
            TreeItem item = child.get(i);
            if (item instanceof TreeSelectItemGroup) {
                ((TreeSelectItemGroup) item).selectAll(b);
            }
            if (b && !isSelect(item)) {
                getSelectItems().add(item);
            }
        }
        if (isMultistage){
            parentCheckSelect();
        }
    }

    public void selectAll(boolean b) {
        this.selectAll(b, false);
    }

    /**
     * 包含的子级是否有选中
     */
    public boolean isSelect() {
        return !getSelectItems().isEmpty();
    }

    /**
     * 是否选中
     *
     * @return
     */
    public boolean isSelect(TreeItem item) {
        return getSelectItems().contains(item);
    }

    @Override
    public boolean onInterceptClick(TreeItem child) {
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
    protected void selectItem(@NonNull TreeItem child, boolean isMultistage) {
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
                    if (((TreeSelectItemGroup) child).isSelect()) {
                        return;
                    }
                }
                getSelectItems().remove(index);
            }
            if (isMultistage) {
                parentCheckSelect();
            }
        }
    }

    protected void selectItem(@NonNull TreeItem child) {
        this.selectItem(child, false);
    }

    public void parentCheckSelect() {
        TreeItemGroup parentItem = getParentItem();
        if (parentItem instanceof TreeSelectItemGroup) {
            // 如果当前选中了,但是父类没有选中,则更新添加
            if (isSelect() != ((TreeSelectItemGroup) parentItem).isSelect(this)) {
                ((TreeSelectItemGroup) parentItem).selectItem(this, true);
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
