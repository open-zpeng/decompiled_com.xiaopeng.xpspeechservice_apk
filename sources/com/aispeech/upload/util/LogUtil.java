package com.aispeech.upload.util;
/* loaded from: classes.dex */
public class LogUtil {
    public static boolean OPEN_LOG = false;

    public static void openLog(String str) {
        OPEN_LOG = true;
        Log.init(3, str);
    }

    public static void d(String str, String str2) {
        if (OPEN_LOG) {
            Log.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (OPEN_LOG) {
            Log.e(str, str2);
        }
    }
}
