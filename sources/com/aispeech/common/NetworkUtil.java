package com.aispeech.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* loaded from: classes.dex */
public class NetworkUtil {
    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context != null && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }
}
