package com.aispeech.upload.http;

import android.text.TextUtils;
import com.aispeech.common.URLUtils;
import com.aispeech.upload.ConfigRequestBean;
import com.aispeech.upload.util.Log;
/* loaded from: classes.dex */
public class HttpUrlValue {
    private static final String BASE_URL = "http://log.aispeech.com";
    private static final String CONFIG_URL = "/config";
    private static final String JSON_URL = "/busng";
    private static final String UPERROR_URL = "/bus";
    private static final String UPFILE_URL = "/upfile";
    private String mUpfileEndUrl;

    public HttpUrlValue(int i, String str) {
        this.mUpfileEndUrl = "";
        this.mUpfileEndUrl = "?logId=" + i + "&productId=" + str;
    }

    public String getJsonUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            str = BASE_URL;
        }
        Log.d("HTTP", "getJsonUrl = " + str + JSON_URL);
        return str + JSON_URL;
    }

    public String getUpfileUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            str = BASE_URL;
        }
        String str2 = str + UPFILE_URL + this.mUpfileEndUrl;
        Log.d("HTTP", "upfileUrl = " + str2);
        return str2;
    }

    public String getErrorUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            str = BASE_URL;
        }
        return str + UPERROR_URL;
    }

    public static String getConfigUrl(ConfigRequestBean configRequestBean) {
        String httpHeadUrl = configRequestBean.getHttpHeadUrl();
        if (TextUtils.isEmpty(httpHeadUrl)) {
            httpHeadUrl = BASE_URL;
        }
        StringBuilder sb = new StringBuilder(httpHeadUrl + CONFIG_URL);
        sb.append(URLUtils.URL_PATH_SEPERATOR);
        sb.append(configRequestBean.getLogId());
        if (!TextUtils.isEmpty(configRequestBean.getProductId()) && !TextUtils.isEmpty(configRequestBean.getDeviceId())) {
            sb.append("?productId=");
            sb.append(configRequestBean.getProductId());
            sb.append("&deviceId=");
            sb.append(configRequestBean.getDeviceId());
        } else if (!TextUtils.isEmpty(configRequestBean.getProductId())) {
            sb.append("?productId=");
            sb.append(configRequestBean.getProductId());
        } else if (!TextUtils.isEmpty(configRequestBean.getDeviceId())) {
            sb.append("?deviceId=");
            sb.append(configRequestBean.getDeviceId());
        }
        String sb2 = sb.toString();
        Log.d("HTTP", "configUrl = " + sb2);
        return sb2;
    }
}
