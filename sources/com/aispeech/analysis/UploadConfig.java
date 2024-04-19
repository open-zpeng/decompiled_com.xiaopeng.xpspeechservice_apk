package com.aispeech.analysis;

import android.content.Context;
import com.aispeech.lite.c;
import com.aispeech.upload.ConfigRequestBean;
import com.aispeech.upload.UploadUtil;
/* loaded from: classes.dex */
public class UploadConfig implements IAnalysisConfig {
    private static final String TAG = "UploadConfig";
    private String uploadUrl;

    public UploadConfig() {
        this(c.s);
    }

    public UploadConfig(String str) {
        this.uploadUrl = str;
    }

    @Override // com.aispeech.analysis.IAnalysisConfig
    public String getUploadConfig(Context context, int i, String str, String str2) {
        ConfigRequestBean configRequestBean = new ConfigRequestBean(i);
        configRequestBean.setProductId(str);
        configRequestBean.setDeviceId(str2);
        configRequestBean.setTimeOut(5000);
        configRequestBean.setHttpHeadUrl(this.uploadUrl);
        return UploadUtil.getConfigInfo(context, configRequestBean);
    }
}
