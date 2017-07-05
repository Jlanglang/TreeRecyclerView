package com.baozi.demo.moudle.testlist.bean;

import com.baozi.treerecyclerview.base.BaseItemData;

import java.util.List;

/**
 * Created by baozi on 2017/4/27.
 */
public class TitleBean extends BaseItemData {
    public List<ContentBean> getSingleBeen() {
        return mSingleBeen;
    }

    public void setSingleBeen(List<ContentBean> singleBeen) {
        mSingleBeen = singleBeen;
    }

    List<ContentBean> mSingleBeen;
}
