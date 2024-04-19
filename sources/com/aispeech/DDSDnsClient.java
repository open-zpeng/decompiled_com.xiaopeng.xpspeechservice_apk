package com.aispeech;

import com.aispeech.common.Log;
/* loaded from: classes.dex */
public class DDSDnsClient {
    public static native String dds_get_host_by_name(String str, int i, int i2, String str2);

    static {
        try {
            Log.d("DDSDnsClient", "before load duidns library");
            System.loadLibrary("duidns");
            Log.d("DDSDnsClient", "after load duidns library");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            Log.e(Log.ERROR_TAG, "Please check useful libduidns.so, and put it in your libs dir!");
        }
    }
}
