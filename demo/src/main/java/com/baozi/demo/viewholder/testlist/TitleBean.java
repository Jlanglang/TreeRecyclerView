package com.baozi.demo.viewholder.testlist;

import com.baozi.treerecyclerview.view.ItemData;

import java.util.List;

/**
 * Created by baozi on 2017/4/27.
 */
public class TitleBean extends ItemData {
    public List<ContentBean> getSingleBeen() {
        return mSingleBeen;
    }

    public void setSingleBeen(List<ContentBean> singleBeen) {
        mSingleBeen = singleBeen;
    }

    List<ContentBean> mSingleBeen;
}
