package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.viewholder.TreeParentItem;
import com.baozi.treerecyclerview.viewholder.TreeItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class FourItem extends TreeParentItem<String> {

    public FourItem(TreeParentItem parentItem, String data) {
        super(data, parentItem);
    }

    @Override
    protected List<TreeItem> initChildsList(String data) {
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FiveItem threeItem = new FiveItem("我是五级");
            if (i % 4 == 0) {//偷个懒,不多写布局了.
                threeItem.setLayoutId(R.layout.itme_one);
                threeItem.setSpanSize(0);
            } else if (i % 3 == 0) {
                threeItem.setLayoutId(R.layout.item_two);
                threeItem.setSpanSize(2);
            }
            treeItems.add(threeItem);
        }
        return treeItems;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_four;
    }

    @Override
    protected int initSpansize() {
        return 2;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {

    }
}
