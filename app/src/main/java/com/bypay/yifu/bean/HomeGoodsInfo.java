package com.bypay.yifu.bean;

public class HomeGoodsInfo {

    private String goodsName;
    private String goodsAmt;
    private String goodsOldAmt;
    private String goodsUrl;
    private String goodsTitle;
    private String goodsHint;
    private int goodsImage;

    public HomeGoodsInfo(String goodsTitle, String goodsHint, int goodsImage) {
        this.goodsTitle = goodsTitle;
        this.goodsHint = goodsHint;
        this.goodsImage = goodsImage;
    }

    public HomeGoodsInfo(String goodsName, String goodsAmt, String goodsOldAmt, int goodsImage) {
        this.goodsName = goodsName;
        this.goodsAmt = goodsAmt;
        this.goodsOldAmt = goodsOldAmt;
        this.goodsImage = goodsImage;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsHint() {
        return goodsHint;
    }

    public void setGoodsHint(String goodsHint) {
        this.goodsHint = goodsHint;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsAmt() {
        return goodsAmt;
    }

    public void setGoodsAmt(String goodsAmt) {
        this.goodsAmt = goodsAmt;
    }

    public String getGoodsOldAmt() {
        return goodsOldAmt;
    }

    public void setGoodsOldAmt(String goodsOldAmt) {
        this.goodsOldAmt = goodsOldAmt;
    }

    public int getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(int goodsImage) {
        this.goodsImage = goodsImage;
    }
}
