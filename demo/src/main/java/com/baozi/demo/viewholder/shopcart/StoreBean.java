package com.baozi.demo.viewholder.shopcart;

import java.util.List;

/**
 * Created by baozi on 2016/12/22.
 */
public class StoreBean {
    private List<ShopListBean> mShopListBeen;
    private String title;
    private int storeId;
    private String storeName;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private boolean isCheck;

    public List<ShopListBean> getShopListBeen() {
        return mShopListBeen;
    }

    public void setShopListBeen(List<ShopListBean> shopListBeen) {
        mShopListBeen = shopListBeen;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

}
