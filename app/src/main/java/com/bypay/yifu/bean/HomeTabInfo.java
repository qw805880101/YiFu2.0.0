package com.bypay.yifu.bean;

public class HomeTabInfo {

    private String tabName;
    private String tabId;
    private String tabUrl;
    private int tabImage;

    public HomeTabInfo(String tabName, int tabImage) {
        this.tabName = tabName;
        this.tabImage = tabImage;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getTabUrl() {
        return tabUrl;
    }

    public void setTabUrl(String tabUrl) {
        this.tabUrl = tabUrl;
    }

    public int getTabImage() {
        return tabImage;
    }

    public void setTabImage(int tabImage) {
        this.tabImage = tabImage;
    }
}
