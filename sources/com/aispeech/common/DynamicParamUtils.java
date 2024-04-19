package com.aispeech.common;

import com.aispeech.export.exception.IllegalPinyinException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class DynamicParamUtils {
    public static final String TAG = "DynamicParamUtils";

    public static String getWakeupWordsParams(String[] strArr, float[] fArr, int[] iArr, boolean z) throws IllegalPinyinException {
        boolean z2;
        if (z) {
            for (String str : strArr) {
                if (!PinYinUtils.checkPinyin(str)) {
                    throw new IllegalPinyinException();
                }
            }
        }
        if (strArr == null || strArr.length == 0 || fArr == null || fArr.length == 0 || iArr == null || iArr.length == 0) {
            z2 = false;
        } else if (strArr.length != fArr.length || strArr.length != iArr.length) {
            throw new IllegalPinyinException("illegal wakeup setting");
        } else {
            z2 = true;
        }
        if (!z2) {
            Log.e(TAG, "drop illegal wakeup params setting");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("words=");
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i].trim());
            if (i < strArr.length - 1) {
                sb.append(",");
            }
        }
        sb.append(";thresh=");
        for (int i2 = 0; i2 < fArr.length; i2++) {
            sb.append(String.format("%.3f", Float.valueOf(fArr[i2])));
            if (i2 < fArr.length - 1) {
                sb.append(",");
            }
        }
        sb.append(";major=");
        for (int i3 = 0; i3 < iArr.length; i3++) {
            sb.append(iArr[i3]);
            if (i3 < iArr.length - 1) {
                sb.append(",");
            }
        }
        sb.append(";");
        HashMap hashMap = new HashMap();
        hashMap.put("env", sb.toString());
        return getDynamicParam(hashMap);
    }

    public static String getDynamicParam(Map<String, ?> map) {
        if (map == null || map.isEmpty()) {
            Log.i(TAG, "dynamicParam: dynamicParam isEmpty ");
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            try {
                jSONObject.put(str, map.get(str));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "dynamicParam " + e);
            }
        }
        return jSONObject.toString();
    }
}
