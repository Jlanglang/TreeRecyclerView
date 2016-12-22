package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
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
            if (i % 4 == 0) {//偷个懒,不多写布局了.
                threeItem.setLayoutId(R.layout.itme_one);
                threeItem.setSpanSize(0);
            } else if (i % 3 == 0) {
                threeItem.setLayoutId(R.layout.item_two);
                threeItem.setSpanSize(2);
            }
            treeAdapterItems.add(threeItem);
        }
        return treeAdapterItems;
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
