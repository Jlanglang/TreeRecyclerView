package com.baozi.treerecyclerview.view;



import com.baozi.treerecyclerview.base.BaseItem;
import com.baozi.treerecyclerview.base.BaseItemData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by baozi on 2016/12/22.
 */

public abstract class TreeSelectItemGroup<D extends BaseItemData>
        extends TreeItemGroup<D> {
    private List<BaseItem> selectItems;

    public List<BaseItem> getSelectItems() {
        if (selectItems == null) {
            selectItems = new ArrayList<>();
        }
        return selectItems;
    }

    public boolean isHaveCheck() {
        return !getSelectItems().isEmpty();
    }

    @Override
    public boolean onInterceptClick(TreeItem child) {
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
        return super.onInterceptClick(child);
    }


    public abstract SelectFlag selectFlag();

    /**
     * Created by baozi on 2017/4/29.
     */
    public enum SelectFlag {
        SINGLE_CHOICE,
        MULTIPLE_CHOICE
    }
}
