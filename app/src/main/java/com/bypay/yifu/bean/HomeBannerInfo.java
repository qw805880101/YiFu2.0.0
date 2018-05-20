package com.bypay.yifu.bean;

import java.util.List;

public class HomeBannerInfo {

    private String bannerName;
    private String bannerUrl;
    private String bannerType;
    private int bannerImage;
    private List<HomeGoodsInfo> homeGoodsInfos;

    public HomeBannerInfo(String bannerType, String bannerName, int bannerImage) {
        this.bannerType = bannerType;
        this.bannerName = bannerName;
        this.bannerImage = bannerImage;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public int getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(int bannerImage) {
        this.bannerImage = bannerImage;
    }

    public List<HomeGoodsInfo> getHomeGoodsInfos() {
        return homeGoodsInfos;
    }

    public void setHomeGoodsInfos(List<HomeGoodsInfo> homeGoodsInfos) {
        this.homeGoodsInfos = homeGoodsInfos;
    }
}
