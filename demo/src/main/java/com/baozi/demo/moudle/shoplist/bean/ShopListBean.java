package com.baozi.demo.moudle.shoplist.bean;

import com.baozi.treerecyclerview.base.BaseItemData;

/**
 * Created by baozi on 2016/12/22.
 */
public class ShopListBean extends BaseItemData {
    private String imgUrl;
    private String shopName;
    private int shopId;
    private int shopNum;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private boolean isCheck;
    public int getShopNum() {
        return shopNum;
    }

    public void setShopNum(int shopNum) {
        this.shopNum = shopNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public int getViewItemType() {
        return 0;
    }
}
