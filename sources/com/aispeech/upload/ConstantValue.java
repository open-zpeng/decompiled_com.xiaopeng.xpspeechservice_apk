package com.aispeech.upload;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
class ConstantValue {
    private String baseUrl;
    private String callerType;
    private String callerVersion;
    private String dbName;
    private String deviceId;
    private String duicoreVersion;
    private int logId;
    private String productId;
    private String productVersion;
    private String project;
    private String userId;
    private String version;
    private String platform = "android";
    private String sdkVersion = BuildConfig.VERSION_NAME;
    private String protVersion = "1.0";
    private int maxCacheNums = Integer.MAX_VALUE;
    private boolean uploadImmediately = false;
    private boolean autoDeleFile = true;
    private Map<String, Object> msgMap = new HashMap();
    private boolean deleFileWhenNetError = false;

    public boolean isDeleFileWhenNetError() {
        return this.deleFileWhenNetError;
    }

    public void setDeleFileWhenNetError(boolean z) {
        this.deleFileWhenNetError = z;
    }

    public void addEntry(String str, Object obj) {
        this.msgMap.put(str, obj);
    }

    public Map<String, Object> getMsgMap() {
        return this.msgMap;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public int getLogId() {
        return this.logId;
    }

    public void setLogId(int i) {
        this.logId = i;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getProductVersion() {
        return this.productVersion;
    }

    public void setProductVersion(String str) {
        this.productVersion = str;
    }

    public String getDuicoreVersion() {
        return this.duicoreVersion;
    }

    public void setDuicoreVersion(String str) {
        this.duicoreVersion = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String str) {
        this.project = str;
    }

    public String getCallerType() {
        return this.callerType;
    }

    public void setCallerType(String str) {
        this.callerType = str;
    }

    public String getCallerVersion() {
        return this.callerVersion;
    }

    public void setCallerVersion(String str) {
        this.callerVersion = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public String getProtVersion() {
        return this.protVersion;
    }

    public void setProtVersion(String str) {
        this.protVersion = str;
    }

    public int getMaxCacheNums() {
        return this.maxCacheNums;
    }

    public void setMaxCacheNums(int i) {
        this.maxCacheNums = i;
    }

    public boolean isUploadImmediately() {
        return this.uploadImmediately;
    }

    public void setUploadImmediately(boolean z) {
        this.uploadImmediately = z;
    }

    public boolean isAutoDeleFile() {
        return this.autoDeleFile;
    }

    public void setAutoDeleFile(boolean z) {
        this.autoDeleFile = z;
    }

    public String getDbName() {
        if (TextUtils.isEmpty(this.dbName)) {
            return this.logId + "_";
        }
        return this.logId + "_" + this.dbName;
    }

    public void setDbName(String str) {
        this.dbName = str;
    }

    public String toString() {
        return "ConstantValue{baseUrl='" + this.baseUrl + "', productId='" + this.productId + "', logId=" + this.logId + ", dbName='" + this.dbName + "', deviceId='" + this.deviceId + "', productVersion='" + this.productVersion + "', duicoreVersion='" + this.duicoreVersion + "', version='" + this.version + "', userId='" + this.userId + "', project='" + this.project + "', callerType='" + this.callerType + "', callerVersion='" + this.callerVersion + "', platform='" + this.platform + "', sdkVersion='" + this.sdkVersion + "', protVersion='" + this.protVersion + "', maxCacheNums=" + this.maxCacheNums + ", uploadImmediately=" + this.uploadImmediately + ", autoDeleFile=" + this.autoDeleFile + ", msgMap=" + this.msgMap + ", deleFileWhenNetError=" + this.deleFileWhenNetError + '}';
    }
}
