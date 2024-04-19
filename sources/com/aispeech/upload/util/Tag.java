package com.aispeech.upload.util;

import android.text.TextUtils;
/* loaded from: classes.dex */
public class Tag {
    private static final String TAG = "AI-Upload";

    public static String getTag(String str) {
        if (TextUtils.isEmpty(str)) {
            return TAG;
        }
        return "AI-Upload-" + str;
    }
}
