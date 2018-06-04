package com.baozi.demo.bean;

import com.baozi.demo.item.testlist.ContentGroupItem;
import com.baozi.treerecyclerview.annotation.TreeItemClass;

import java.util.List;

/**
 * Created by baozi on 2017/4/27.
 */
@TreeItemClass(iClass = ContentGroupItem.class)
public class TitleBean {
    public List<ContentBean> getSingleBeen() {
        return mSingleBeen;
    }

    public void setSingleBeen(List<ContentBean> singleBeen) {
        mSingleBeen = singleBeen;
    }

    List<ContentBean> mSingleBeen;
}
