package com.aispeech.upload;
/* loaded from: classes.dex */
public class ConfigRequestBean {
    private String deviceId;
    private String httpHeadUrl;
    private int logId;
    private String productId;
    private int timeOut;

    public ConfigRequestBean(int i) {
        this.logId = i;
    }

    public int getLogId() {
        return this.logId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public void setTimeOut(int i) {
        this.timeOut = i;
    }

    public String getHttpHeadUrl() {
        return this.httpHeadUrl;
    }

    public void setHttpHeadUrl(String str) {
        this.httpHeadUrl = str;
    }
}
