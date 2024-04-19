package com.aispeech.analysis;

import android.content.Context;
import java.util.Map;
/* loaded from: classes.dex */
public class AnalysisParam {
    private String callerType;
    private Context context;
    private String deviceId;
    private int logID;
    private boolean logcatDebugable;
    private String logfilePath;
    private Map<String, Object> map;
    private int maxCacheNum;
    private String productId;
    private String project;
    private String sdkVersion;
    private boolean uploadImmediately;
    private String uploadUrl;

    public AnalysisParam(Builder builder) {
        this.context = builder.context;
        this.uploadUrl = builder.uploadUrl;
        this.logID = builder.logID;
        this.project = builder.project;
        this.callerType = builder.callerType;
        this.productId = builder.productId;
        this.deviceId = builder.deviceId;
        this.sdkVersion = builder.sdkVersion;
        this.logcatDebugable = builder.logcatDebugable;
        this.logfilePath = builder.logfilePath;
        this.uploadImmediately = builder.uploadImmediately;
        this.maxCacheNum = builder.maxCacheNum;
        this.map = builder.map;
    }

    public Context getContext() {
        return this.context;
    }

    public String getUploadUrl() {
        return this.uploadUrl;
    }

    public int getLogID() {
        return this.logID;
    }

    public String getProject() {
        return this.project;
    }

    public String getCallerType() {
        return this.callerType;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public boolean isLogcatDebugable() {
        return this.logcatDebugable;
    }

    public String getLogfilePath() {
        return this.logfilePath;
    }

    public boolean isUploadImmediately() {
        return this.uploadImmediately;
    }

    public int getMaxCacheNum() {
        return this.maxCacheNum;
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    public String toString() {
        return "AnalysisParam{context=" + this.context + ", uploadUrl='" + this.uploadUrl + "', logID=" + this.logID + ", project='" + this.project + "', callerType='" + this.callerType + "', productId='" + this.productId + "', deviceId='" + this.deviceId + "', sdkVersion='" + this.sdkVersion + "', logcatDebugable=" + this.logcatDebugable + ", logfilePath='" + this.logfilePath + "', uploadImmediately=" + this.uploadImmediately + ", maxCacheNum=" + this.maxCacheNum + '}';
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String callerType;
        private Context context;
        private String deviceId;
        private int logID;
        private boolean logcatDebugable;
        private String logfilePath;
        private Map<String, Object> map;
        private int maxCacheNum;
        private String productId;
        private String project;
        private String sdkVersion;
        private boolean uploadImmediately;
        private String uploadUrl;

        public Context getContext() {
            return this.context;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public String getUploadUrl() {
            return this.uploadUrl;
        }

        public Builder setUploadUrl(String str) {
            this.uploadUrl = str;
            return this;
        }

        public int getLogID() {
            return this.logID;
        }

        public Builder setLogID(int i) {
            this.logID = i;
            return this;
        }

        public String getProject() {
            return this.project;
        }

        public Builder setProject(String str) {
            this.project = str;
            return this;
        }

        public String getCallerType() {
            return this.callerType;
        }

        public Builder setCallerType(String str) {
            this.callerType = str;
            return this;
        }

        public String getProductId() {
            return this.productId;
        }

        public Builder setProductId(String str) {
            this.productId = str;
            return this;
        }

        public String getDeviceId() {
            return this.deviceId;
        }

        public Builder setDeviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public String getSdkVersion() {
            return this.sdkVersion;
        }

        public Builder setSdkVersion(String str) {
            this.sdkVersion = str;
            return this;
        }

        public boolean isLogcatDebugable() {
            return this.logcatDebugable;
        }

        public Builder setLogcatDebugable(boolean z) {
            this.logcatDebugable = z;
            return this;
        }

        public String getLogfilePath() {
            return this.logfilePath;
        }

        public Builder setLogfilePath(String str) {
            this.logfilePath = str;
            return this;
        }

        public boolean isUploadImmediately() {
            return this.uploadImmediately;
        }

        public Builder setUploadImmediately(boolean z) {
            this.uploadImmediately = z;
            return this;
        }

        public int getMaxCacheNum() {
            return this.maxCacheNum;
        }

        public Builder setMaxCacheNum(int i) {
            this.maxCacheNum = i;
            return this;
        }

        public Map<String, Object> getMap() {
            return this.map;
        }

        public Builder setMap(Map<String, Object> map) {
            this.map = map;
            return this;
        }

        public AnalysisParam create() {
            return new AnalysisParam(this);
        }
    }
}
