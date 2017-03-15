package com.baozi.demo.viewholder.citytree;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.ViewHolder;
import com.baozi.treerecyclerview.view.TreeParentItem;

/**
 * Created by baozi on 2016/12/8.
 */
public class FourItem extends TreeParentItem<String> {

    public FourItem(String data) {
        super(data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_four;
    }

    @Override
    protected void initChildsList(String data) {
        for (int i = 0; i < 10; i++) {
            FiveItem threeItem = new FiveItem("我是五级");
            if (i % 4 == 0) {//偷个懒,不多写布局了.
                threeItem.setSpanSize(0);
            } else if (i % 3 == 0) {
                threeItem.setSpanSize(2);
            }
            addView(threeItem);
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {

    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }
}
