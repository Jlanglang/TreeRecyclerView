package com.baozi.demo.viewholder.shopcart;

import java.util.List;

/**
 * @author jlanglang  2017/3/15 15:37
 * @版本 2.0
 * @Change
 */
public class BrandBean {
    private boolean mCheck;

    public List<StoreBean> getStoreBeens() {
        return mStoreBeens;
    }

    public void setStoreBeens(List<StoreBean> storeBeens) {
        mStoreBeens = storeBeens;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    private List<StoreBean> mStoreBeens;
    private String title;
    private int brandId;
    private String brandName;

    public void setCheck(boolean check) {
        mCheck = check;
    }

    public boolean isCheck() {
        return mCheck;
    }
}
