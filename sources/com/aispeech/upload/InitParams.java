package com.aispeech.upload;

import com.aispeech.upload.util.LogUtil;
/* loaded from: classes.dex */
public class InitParams {
    private ConstantValue mConstantValue = new ConstantValue();

    public ConstantValue getConstantValue() {
        return this.mConstantValue;
    }

    public InitParams(int i) {
        this.mConstantValue.setLogId(i);
    }

    public InitParams addEntry(String str, Object obj) {
        this.mConstantValue.addEntry(str, obj);
        return this;
    }

    public InitParams setDeleFileWhenNetError(boolean z) {
        this.mConstantValue.setDeleFileWhenNetError(z);
        return this;
    }

    public InitParams setProject(String str) {
        this.mConstantValue.setProject(str);
        return this;
    }

    public InitParams setProductId(String str) {
        this.mConstantValue.setProductId(str);
        return this;
    }

    public InitParams setDeviceId(String str) {
        this.mConstantValue.setDeviceId(str);
        return this;
    }

    public InitParams setProductVersion(String str) {
        this.mConstantValue.setProductVersion(str);
        return this;
    }

    public InitParams setDuicoreVersion(String str) {
        this.mConstantValue.setDuicoreVersion(str);
        return this;
    }

    public InitParams setVersion(String str) {
        this.mConstantValue.setVersion(str);
        return this;
    }

    public InitParams setUserId(String str) {
        this.mConstantValue.setUserId(str);
        return this;
    }

    public InitParams setCallerType(String str) {
        this.mConstantValue.setCallerType(str);
        return this;
    }

    public InitParams setCallerVersion(String str) {
        this.mConstantValue.setCallerVersion(str);
        return this;
    }

    public InitParams setMaxCacheNum(int i) {
        this.mConstantValue.setMaxCacheNums(i);
        return this;
    }

    public InitParams setHttpUrl(String str) {
        this.mConstantValue.setBaseUrl(str);
        return this;
    }

    public InitParams setUploadImmediately() {
        this.mConstantValue.setUploadImmediately(true);
        return this;
    }

    public InitParams setAutoDeleFile(boolean z) {
        this.mConstantValue.setAutoDeleFile(z);
        return this;
    }

    public InitParams openLog() {
        return openLog(null);
    }

    public InitParams openLog(String str) {
        LogUtil.openLog(str);
        return this;
    }

    public InitParams setDBName(String str) {
        this.mConstantValue.setDbName(str);
        return this;
    }
}
