package com.baozi.demo.moudle.testlist.bean;

import com.baozi.treerecyclerview.base.BaseItemData;

/**
 * Created by baozi on 2017/4/27.
 */

public class ContentBean extends BaseItemData {
    private String number;
    private String type;
    private int stateCode;
    private String content;
    private int id;
    private boolean check;

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}
