package com.baozi.treerecyclerview.treeitem;

import com.baozi.treerecyclerview.viewholder.TreeAdapterItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class FourItem extends TreeAdapterItem<String> {
    public FourItem(String data) {
        super(data);
    }

    @Override
    protected List<TreeAdapterItem> initChildsList(String data) {
        ArrayList<TreeAdapterItem> treeAdapterItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FiveItem threeItem = new FiveItem("我是五级");
            treeAdapterItems.add(threeItem);
        }
        return treeAdapterItems;
    }

    @Override
    public int grade() {
        return 4;
    }

    @Override
    public int getSpansize() {
        return 1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {

    }
}
